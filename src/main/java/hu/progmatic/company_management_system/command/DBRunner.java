package hu.progmatic.company_management_system.command;

import hu.progmatic.company_management_system.models.*;
import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import hu.progmatic.company_management_system.models.production.*;
import hu.progmatic.company_management_system.models.sales.Partner;
import hu.progmatic.company_management_system.models.sales.PartnerType;
import hu.progmatic.company_management_system.models.sales.ShippingIn;
import hu.progmatic.company_management_system.models.sales.ShippingOut;
import hu.progmatic.company_management_system.repositories.*;
import hu.progmatic.company_management_system.repositories.EmployeeRepo;
import hu.progmatic.company_management_system.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DBRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ProducedProductRepo producedProductRepo;

    private final PartnerRepo partnerRepo;

    private final BOMListRepo bomListRepo;

    private final ShippingInRepo shippingInRepo;

    private final ShippingOutRepo shippingOutRepo;

    private final IngredientRepo ingredientRepo;

    private final RawMaterialRepo rawMaterialRepo;

    private final EndProductRepo endProductRepo;
    private final EmployeeRepo employeeRepo;

    public DBRunner(UserRepository userRepository, PasswordEncoder passwordEncoder,
                    ProducedProductRepo producedProductRepo, PartnerRepo partnerRepo,
                    BOMListRepo bomListRepo, ShippingInRepo shippingInRepo,
                    ShippingOutRepo shippingOutRepo, IngredientRepo ingredientRepo,
                    RawMaterialRepo rawMaterialRepo, EndProductRepo endProductRepo,
                    EmployeeRepo employeeRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.producedProductRepo = producedProductRepo;
        this.partnerRepo = partnerRepo;
        this.bomListRepo = bomListRepo;
        this.shippingInRepo = shippingInRepo;
        this.shippingOutRepo = shippingOutRepo;
        this.ingredientRepo = ingredientRepo;
        this.rawMaterialRepo = rawMaterialRepo;
        this.endProductRepo = endProductRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new User("admin", passwordEncoder.encode("password"), Position.ADMIN, true));
        System.out.println("Admin user generated.");

        userRepository.save(new User("user", passwordEncoder.encode("password"), Position.WAREHOUSEWORKER, false));
        System.out.println("Warehouse user generated.");

        userRepository.save(new User("Gabi", passwordEncoder.encode("password"), Position.TRADE_MANAGER, false));
        System.out.println("Trade-manager user generated.");

        userRepository.save(new User("Zita", passwordEncoder.encode("password"), Position.FINANCE_STAFF, false));
        System.out.println("Finance-staff user generated.");

        userRepository.save(new User("Gyuri", passwordEncoder.encode("password"), Position.PRODUCTIONMANAGER, false));
        System.out.println("Productionmanager user generated.");

        userRepository.save(new User("Pisti", passwordEncoder.encode("password"), Position.CEO, false));
        System.out.println("CEO user generated.");

        userRepository.save(new User("Janika", passwordEncoder.encode("password"), Position.MACHINE_OPERATOR, false));
        System.out.println("Machine-operator user generated.");

        Partner partner1 = new Partner("Mészáros és Mészáros Kft", PartnerType.SUPPLIER, "1000 BP Hősök tere 1.", "Lölő", "lölő@közpénz.hu", "0630123456789");
        partnerRepo.save(partner1);
        Partner partner2 = new Partner("Mészáros és a Főni Kft", PartnerType.CUSTOMER, "1000 BP Hősök tere 1.", "Főni", "lölő@közpénz.hu", "0630123456789");
        partnerRepo.save(partner2);
        Partner partner3 = new Partner("Vasbeton Bt", PartnerType.CUSTOMER, "2900 Komárom, Fuvaros köz 1.", "Csontos Veronika", "csontosveronika@vasbeton.hu", "0630123456789");
        partnerRepo.save(partner3);
        Partner partner4 = new Partner("Pick-Pack Kft", PartnerType.SUPPLIER, "1201 Budapest, Rózsa utca 7..", "Vadon Emil", "vadonemil@pickpack.hu", "06309876543");
        partnerRepo.save(partner4);
        System.out.println("Partners generated.");

        ProducedProduct producedProduct1 = new ProducedProduct("Motorblokk");
        System.out.println("Products generated.");

        Ingredient ingredient1 = new Ingredient("A alapanyag", "A hozzávaló","m");
        Ingredient ingredient2 = new Ingredient("B alapanyag", "B hozzávaló","kg");


        BOMList bomList1 = new BOMList("Így készül a motorblokk", producedProduct1, List.of(ingredient1, ingredient2), "A alapanyag - 2, B alapanyag 3");
        ingredient1.setBomList(bomList1);
        ingredient2.setBomList(bomList1);
        producedProduct1.setBomList(bomList1);
        producedProductRepo.save(producedProduct1);
        bomListRepo.save(bomList1);
        ingredientRepo.save(ingredient1);
        ingredientRepo.save(ingredient2);
        System.out.println("BOMList1 garanted.");

        ProducedProduct producedProduct2 = new ProducedProduct("Cserebogár");
        ProducedProduct producedProduct3 = new ProducedProduct("Radiátor");
        Ingredient ingredient3 = new Ingredient("C alapanyag", "C hozzávaló","m");
        Ingredient ingredient4 = new Ingredient("D alapanyag", "D hozzávaló","kg");
        Ingredient ingredient5 = new Ingredient("Vas", "radiátor hozzávaló","kg");
        Ingredient ingredient6 = new Ingredient("Csavar", "radiátor hozzávaló","db");
        BOMList bomList2 = new BOMList("Így készül a cserebogár", producedProduct2, List.of(ingredient3, ingredient4), "C alapanyag - 1, D alapanyag 4");
        BOMList bomList3 = new BOMList("Így készül a radiátor", producedProduct3, List.of(ingredient5, ingredient6), "Vas - 15kg, Csavar - 22db");
        ingredient3.setBomList(bomList2);
        ingredient4.setBomList(bomList2);
        ingredient5.setBomList(bomList3);
        ingredient6.setBomList(bomList3);
        producedProduct2.setBomList(bomList2);
        producedProduct3.setBomList(bomList3);
        producedProductRepo.save(producedProduct2);
        producedProductRepo.save(producedProduct3);
        bomListRepo.save(bomList2);
        bomListRepo.save(bomList3);
        ingredientRepo.save(ingredient3);
        ingredientRepo.save(ingredient4);
        ingredientRepo.save(ingredient5);
        ingredientRepo.save(ingredient6);
        System.out.println("BOMLlists and Ingredients generated.");

        ShippingIn shippingIn1 = new ShippingIn(partner1, LocalDate.now(),List.of());
        shippingInRepo.save(shippingIn1);

        RawMaterial rawMaterial1 = new RawMaterial(ingredient1, 20223010,1200,10, shippingIn1, Warehouse.INBOUND);
        rawMaterialRepo.save(rawMaterial1);
        RawMaterial rawMaterial2 = new RawMaterial(ingredient2, 20223011,1200,10, shippingIn1, Warehouse.INBOUND);
        rawMaterialRepo.save(rawMaterial2);

        ShippingIn shippingIn2 = new ShippingIn(partner2, LocalDate.of(2022, 9,20),List.of());
        shippingInRepo.save(shippingIn2);
        RawMaterial rawMaterial3 = new RawMaterial(ingredient3, 20223012,1200,10, shippingIn2, Warehouse.INBOUND);
        rawMaterialRepo.save(rawMaterial3);
        RawMaterial rawMaterial4 = new RawMaterial(ingredient4, 20223013,1200,10, shippingIn2, Warehouse.INBOUND);
        rawMaterialRepo.save(rawMaterial4);

        ShippingIn shippingIn3 = new ShippingIn(partner3, LocalDate.of(2022, 10,15),List.of());
        shippingInRepo.save(shippingIn3);
        RawMaterial rawMaterial5 = new RawMaterial(ingredient5, 20224015,2000,20, shippingIn3 ,Warehouse.INBOUND);
        rawMaterialRepo.save(rawMaterial5);
        RawMaterial rawMaterial6 = new RawMaterial(ingredient6, 20224014,2150,20, shippingIn3, Warehouse.INBOUND);
        rawMaterialRepo.save(rawMaterial6);

        System.out.println("Shipping ins and Rawmaterials generated.");

        ShippingOut shippingOut1 = new ShippingOut(partner1,LocalDate.now(),List.of());
        shippingOutRepo.save(shippingOut1);
        EndProduct endProduct1 = new EndProduct(producedProduct1, 5990075,80000,10,shippingOut1);
        endProductRepo.save(endProduct1);

        ShippingOut shippingOut2 = new ShippingOut(partner3,LocalDate.now(),List.of());
        shippingOutRepo.save(shippingOut2);
        EndProduct endProduct2 = new EndProduct(producedProduct3, 5997789,100000,10,shippingOut2);
        endProductRepo.save(endProduct2);
        System.out.println("Shipping outs and Endproducts generated.");

        employeeRepo.save(new Employee("8312378998", "Hajdú Nikolett", "Hajdú Nikolett", "023546325",
                "Eger", LocalDate.of(1980,12,13),
                "Kiss Erika", "1022 Bp, Mogyoró u. 12.", "Alkalmazott",
                LocalDate.of(2020,11,11), "3212", 8, 800000));
        employeeRepo.save(new Employee("8456987558", "Magyar Sándor", "Magyar Sándor", "312855423",
                "Budapest", LocalDate.of(1993,10,3),
                "Major Kinga", "1035 Bp, Vörösvári út 7.", "Alkalmazott",
                LocalDate.of(2021,4,5), "9310", 8, 500000));
        employeeRepo.save(new Employee("8369754421", "Kecskés Brigitta", "Nagy Brigitta", "045897645",
                "Miskolc", LocalDate.of(1979,10,5),
                "Kajer Anett", "1076 Bp, Péterfy Sándor utca 7.", "Alkalmazott",
                LocalDate.of(2020,11,11), "1411", 8, 800000));
        employeeRepo.save(new Employee("8313256455", "Laub János", "Laub János", "012554265",
                "Nyíregyháza", LocalDate.of(1971,1,6),
                "Balog Zsuzsanna", "2500 Esztergom, Vidám utca 2.", "Alkalmazott",
                LocalDate.of(2020,12,1), "3212", 8, 900000));
        employeeRepo.save(new Employee("8475000253", "Héni Péter", "Héni Péter", "093366915",
                "Székesfehérvár", LocalDate.of(1988,2,23),
                "Ilik Marianna", "2360 Gyál, Táncsics Mihály utca 163.", "Alkalmazott",
                LocalDate.of(2021,5,12), "9310", 6, 400000));
        employeeRepo.save(new Employee("8459658733", "Molnár Ödön", "Molnár Ödön", "022868276",
                "Budapest", LocalDate.of(1985,7,7),
                "Kun Izabella", "1161 Bp, Csillag utca 3.", "Alkalmazott",
                LocalDate.of(1980,1,17), "9310", 8, 500000));
    }
}

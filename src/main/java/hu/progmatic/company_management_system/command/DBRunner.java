package hu.progmatic.company_management_system.command;

import hu.progmatic.company_management_system.models.*;
import hu.progmatic.company_management_system.repositories.*;
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


    public DBRunner(UserRepository userRepository, PasswordEncoder passwordEncoder, ProducedProductRepo producedProductRepo, PartnerRepo partnerRepo, BOMListRepo bomListRepo, ShippingInRepo shippingInRepo, ShippingOutRepo shippingOutRepo, IngredientRepo ingredientRepo, RawMaterialRepo rawMaterialRepo, EndProductRepo endProductRepo) {
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
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new User("user", passwordEncoder.encode("password")));
        System.out.println("User user generated.");

        userRepository.save(new User("admin", passwordEncoder.encode("password"), Position.PRODUCTIONMANAGER, true));
        System.out.println("Admin user generated.");

        Partner partner1 = new Partner("Mészáros és Mészáros Kft", PartnerType.SUPPLIER, "1000 BP Hősök tere 1.", "Lölő", "lölő@közpénz.hu", "0630123456789");
        partnerRepo.save(partner1);
        Partner partner2 = new Partner("Mészáros és a Főni Kft", PartnerType.COSTUMER, "1000 BP Hősök tere 1.", "Főni", "lölő@közpénz.hu", "0630123456789");
        partnerRepo.save(partner2);
        System.out.println("Partners generated.");

        ProducedProduct producedProduct1 = new ProducedProduct("Motorblokk");
        System.out.println("Products generated.");

        Ingredient ingredient1 = new Ingredient("A alapanyag", "A hozzávaló","m");
        Ingredient ingredient2 = new Ingredient("B alapanyag", "B hozzávaló","kg");
        BOMList bomList1 = new BOMList("Így készül a motorblokk", producedProduct1, List.of(ingredient1, ingredient2));
        ingredient1.setBomList(bomList1);
        ingredient2.setBomList(bomList1);
        producedProduct1.setBomList(bomList1);
        producedProductRepo.save(producedProduct1);
        bomListRepo.save(bomList1);
        ingredientRepo.save(ingredient1);
        ingredientRepo.save(ingredient2);
        System.out.println("BOMList1 garanted.");

        ProducedProduct producedProduct2 = new ProducedProduct("Cserebogár");
        Ingredient ingredient3 = new Ingredient("C alapanyag", "C hozzávaló","m");
        Ingredient ingredient4 = new Ingredient("D alapanyag", "D hozzávaló","kg");
        BOMList bomList2 = new BOMList("Így készül a cserebogár", producedProduct2, List.of(ingredient3, ingredient4));
        ingredient3.setBomList(bomList2);
        ingredient4.setBomList(bomList2);
        producedProduct2.setBomList(bomList2);
        producedProductRepo.save(producedProduct2);
        bomListRepo.save(bomList2);
        ingredientRepo.save(ingredient3);
        ingredientRepo.save(ingredient4);
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
        System.out.println("Shipping ins and Rawmaterials generated.");

        ShippingOut shippingOut1 = new ShippingOut(partner1,LocalDate.now(),List.of());
        shippingOutRepo.save(shippingOut1);
        EndProduct endProduct1 = new EndProduct(producedProduct1, 5990075,80000,10,shippingOut1);
        endProductRepo.save(endProduct1);
        System.out.println("Shipping outs and Endproducts generated.");
    }
}

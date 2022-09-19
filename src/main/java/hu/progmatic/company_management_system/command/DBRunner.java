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

    private final ProductRepo productRepo;

    private final PartnerRepo partnerRepo;

    private final BOMListRepo bomListRepo;

    private final ShippingRepo shippingRepo;

    private final IngredientRepo ingredientRepo;

    private final RawMaterialRepo rawMaterialRepo;


    public DBRunner(UserRepository userRepository, PasswordEncoder passwordEncoder, ProductRepo productRepo, PartnerRepo partnerRepo, BOMListRepo bomListRepo, ShippingRepo shippingRepo, IngredientRepo ingredientRepo, RawMaterialRepo rawMaterialRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.productRepo = productRepo;
        this.partnerRepo = partnerRepo;
        this.bomListRepo = bomListRepo;
        this.shippingRepo = shippingRepo;
        this.ingredientRepo = ingredientRepo;
        this.rawMaterialRepo = rawMaterialRepo;
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

        Product product1 = new Product("Motorblokk", 5990035, 1500000);
        System.out.println("Products generated.");

        Ingredient ingredient1 = new Ingredient("A alapanyag", "A hozzávaló","m");
        Ingredient ingredient2 = new Ingredient("B alapanyag", "B hozzávaló","kg");
        BOMList bomList1 = new BOMList("Így készül a motorblokk",product1, List.of(ingredient1, ingredient2));
        ingredient1.setBomList(bomList1);
        ingredient2.setBomList(bomList1);
        productRepo.save(product1);
        bomListRepo.save(bomList1);
        ingredientRepo.save(ingredient1);
        ingredientRepo.save(ingredient2);

        Product product2 = new Product("Cserebogár", 5990099, 4200000);
        Ingredient ingredient3 = new Ingredient("C alapanyag", "C hozzávaló","m");
        Ingredient ingredient4 = new Ingredient("D alapanyag", "D hozzávaló","kg");
        BOMList bomList2 = new BOMList("Így készül a cserebogár",product2, List.of(ingredient3, ingredient4));
        ingredient3.setBomList(bomList2);
        ingredient4.setBomList(bomList2);
        productRepo.save(product2);
        bomListRepo.save(bomList2);
        ingredientRepo.save(ingredient3);
        ingredientRepo.save(ingredient4);
        System.out.println("BOMLlists and Ingredients generated.");

        Shipping shipping1 = new Shipping(partner1,partner2, LocalDate.now(),List.of());
        shippingRepo.save(shipping1);
        RawMaterial rawMaterial1 = new RawMaterial(ingredient1, 20223010,1200,10, shipping1, Warehouse.INBOUND);
        rawMaterialRepo.save(rawMaterial1);
        RawMaterial rawMaterial2 = new RawMaterial(ingredient2, 21223010,1200,10, shipping1, Warehouse.INBOUND);
        rawMaterialRepo.save(rawMaterial2);

        Shipping shipping2 = new Shipping(partner2,partner1, LocalDate.of(2022, 9,20),List.of());
        shippingRepo.save(shipping2);
        RawMaterial rawMaterial3 = new RawMaterial(ingredient3, 20223010,1200,10, shipping2, Warehouse.INBOUND);
        rawMaterialRepo.save(rawMaterial3);
        RawMaterial rawMaterial4 = new RawMaterial(ingredient4, 21223010,1200,10, shipping2, Warehouse.INBOUND);
        rawMaterialRepo.save(rawMaterial4);
        System.out.println("Shippings and Rawmaterials generated.");
    }
}

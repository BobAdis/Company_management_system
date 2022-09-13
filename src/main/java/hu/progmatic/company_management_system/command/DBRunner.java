package hu.progmatic.company_management_system.command;

import hu.progmatic.company_management_system.models.*;
import hu.progmatic.company_management_system.repositories.PartnerRepo;
import hu.progmatic.company_management_system.repositories.ProductRepo;
import hu.progmatic.company_management_system.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DBRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ProductRepo productRepo;

    private final PartnerRepo partnerRepo;


    public DBRunner(UserRepository userRepository, PasswordEncoder passwordEncoder, ProductRepo productRepo, PartnerRepo partnerRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.productRepo = productRepo;
        this.partnerRepo = partnerRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new User("user", passwordEncoder.encode("password")));
        System.out.println("User user generated.");

        userRepository.save(new User("admin", passwordEncoder.encode("password"), Task.PRODUCTIONMANAGER, true));
        System.out.println("Admin user generated.");


        partnerRepo.save(new Partner("Mészáros és Mészáros Kft", PartnerType.SUPPLIER, "1000 BP Hősök tere 1.", "Lölő", "lölő@közpénz.hu", "0630123456789"));
        partnerRepo.save(new Partner("Mészáros és a Főni Kft", PartnerType.COSTUMER, "1000 BP Hősök tere 1.", "Főni", "lölő@közpénz.hu", "0630123456789"));
        System.out.println("Partners generated.");

        productRepo.save(new Product("vas", 5990035, 200, "ez csak egy darab vas", ProductCondition.RAWMATERIAL));
        productRepo.save(new Product("kész vas", 5990035, 200, "ez csak egy darab vas", ProductCondition.FINISHED ));
        productRepo.save(new Product("Selejt vas", 5990035, 200, "ez csak egy darab vas", ProductCondition.FINISHED ));
        productRepo.save(new Product("Munka vas", 5990035, 200, "ez csak egy darab vas", ProductCondition.RAWMATERIAL));
        System.out.println("Products generated.");
    }
}

package hu.progmatic.company_management_system.command;

import hu.progmatic.company_management_system.models.*;
import hu.progmatic.company_management_system.repositories.ProductRepo;
import hu.progmatic.company_management_system.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DBRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ProductRepo productRepo;


    public DBRunner(UserRepository userRepository, PasswordEncoder passwordEncoder, ProductRepo productRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.productRepo = productRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new User("user", passwordEncoder.encode("password")));
        System.out.println("User user generated.");

        userRepository.save(new User("admin", passwordEncoder.encode("password"), Task.PRODUCTIONMANAGER, true));
        System.out.println("Admin user generated.");

        productRepo.save(new Product("vas", 5990035, 200, "ez csak egy darab vas", ProductCondition.RAWMATERIAL, Warehouse.INBOUND));
        productRepo.save(new Product("kész vas", 5990035, 200, "ez csak egy darab vas", ProductCondition.FINISHED, Warehouse.OUTBOND));
        productRepo.save(new Product("Selejt vas", 5990035, 200, "ez csak egy darab vas", ProductCondition.FINISHED, Warehouse.REJECT));
        productRepo.save(new Product("Munka vas", 5990035, 200, "ez csak egy darab vas", ProductCondition.RAWMATERIAL, Warehouse.WORKSTATIONS));
    }
}

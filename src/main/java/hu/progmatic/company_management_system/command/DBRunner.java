package hu.progmatic.company_management_system.command;

import hu.progmatic.company_management_system.models.*;
import hu.progmatic.company_management_system.repositories.EmployeeRepo;
import hu.progmatic.company_management_system.repositories.ProductRepo;
import hu.progmatic.company_management_system.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DBRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ProductRepo productRepo;


    private final EmployeeRepo employeeRepo;


    public DBRunner(UserRepository userRepository, PasswordEncoder passwordEncoder, ProductRepo productRepo, EmployeeRepo employeeRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.productRepo = productRepo;
        this.employeeRepo = employeeRepo;
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

        employeeRepo.save(new Employee("8312378998", "Hajdú Nikolett", "Hajdú Nikolett", "023546325",
                "Eger", LocalDate.of(1980,12,13),
                "Kiss Erika", "1022 Bp, Mogyoró u. 12.", "Alkalmazott", Task.PRODUCTIONMANAGER,
                LocalDate.of(2020,11,11), "3212", 8, 800000));
        employeeRepo.save(new Employee("8456987558", "Magyar Sándor", "Magyar Sándor", "312855423",
                "Budapest", LocalDate.of(1993,10,3),
                "Major Kinga", "1035 Bp, Vörösvári út 7.", "Alkalmazott", Task.WAREHOUSEWORKER,
                LocalDate.of(2021,4,5), "9310", 8, 500000));
        employeeRepo.save(new Employee("8369754421", "Kecskés Brigitta", "Nagy Brigitta", "045897645",
                "Miskolc", LocalDate.of(1979,10,5),
                "Kajer Anett", "1076 Bp, Péterfy Sándor utca 7.", "Alkalmazott", Task.ACCOUNTANT,
                LocalDate.of(2020,11,11), "1411", 8, 800000));
        employeeRepo.save(new Employee("8313256455", "Laub János", "Laub János", "012554265",
                "Nyíregyháza", LocalDate.of(1971,1,6),
                "Balog Zsuzsanna", "2500 Esztergom, Vidám utca 2.", "Alkalmazott", Task.PRODUCTIONMANAGER,
                LocalDate.of(2020,12,1), "3212", 8, 900000));
        employeeRepo.save(new Employee("8475000253", "Héni Péter", "Héni Péter", "093366915",
                "Székesfehérvár", LocalDate.of(1988,2,23),
                "Ilik Marianna", "2360 Gyál, Táncsics Mihály utca 163.", "Alkalmazott", Task.WAREHOUSEWORKER,
                LocalDate.of(2021,5,12), "9310", 6, 400000));
        employeeRepo.save(new Employee("8459658733", "Molnár Ödön", "Molnár Ödön", "022868276",
                "Budapest", LocalDate.of(1985,7,7),
                "Kun Izabella", "1161 Bp, Csillag utca 3.", "Alkalmazott", Task.WAREHOUSEWORKER,
                LocalDate.of(1980,1,17), "9310", 8, 500000));





    }
}

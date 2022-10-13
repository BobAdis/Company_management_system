package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.User;
import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    User findByEmployee(Employee e);



}

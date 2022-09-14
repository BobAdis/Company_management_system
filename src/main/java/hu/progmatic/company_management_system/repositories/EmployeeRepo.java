package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, String> {



}

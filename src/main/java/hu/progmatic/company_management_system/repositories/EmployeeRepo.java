package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.Position;
import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, String> {

 Employee findByTaxNumber(String taxNumber);
 Employee findByPersonnelTask(Position personnelTask);

 List<Employee> findAllByPersonnelTaskIsNull();
 List<Employee> findAllByUserIsNull();
 List<Employee> findAllByUserIsNotNull();

}

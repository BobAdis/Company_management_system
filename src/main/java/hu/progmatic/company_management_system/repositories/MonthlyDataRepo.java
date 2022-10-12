package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import hu.progmatic.company_management_system.models.hr_accounting.MonthlyData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyDataRepo extends CrudRepository<MonthlyData, Long> {



        MonthlyData findMonthlyDataByEmployee(Employee employee);

}

package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.Employee;
import hu.progmatic.company_management_system.repositories.EmployeeRepo;
import hu.progmatic.company_management_system.repositories.MonthlyDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepo employeeRepo;


    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getEmployees(){
        List<Employee> employees = (List<Employee>) employeeRepo.findAll();
        return employees;
    }

    public Employee getEmployeeByTaxNumber (String taxNumber) {
        return employeeRepo.findByTaxNumber(taxNumber);
    }

    public void save(Employee employee) {
        employeeRepo.save(employee);
    }


}

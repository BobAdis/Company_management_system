package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import hu.progmatic.company_management_system.repositories.EmployeeRepo;
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
        return (List<Employee>) employeeRepo.findAll();
    }

    public Employee getEmployeeByTaxNumber (String taxNumber) {
        return employeeRepo.findByTaxNumber(taxNumber);
    }

    public void save(Employee employee) {
        employeeRepo.save(employee);
    }


}

package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.Employee;
import hu.progmatic.company_management_system.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public String getEmployeePage(Model model) {
        List<Employee> employees = employeeService.getEmployees();

        model.addAttribute("employees", employees);
        return "employees";
    }
}

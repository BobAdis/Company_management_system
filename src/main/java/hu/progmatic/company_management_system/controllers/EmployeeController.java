package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.*;
import hu.progmatic.company_management_system.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

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

    @GetMapping("/employee/{taxnumber}")
    public String getOneEmployee(@PathVariable String taxnumber, Model model) {
        Employee e1 = employeeService.getEmployeeByTaxNumber(taxnumber);
        model.addAttribute("oneEmployee", e1);
        return "actualEmployee";
    }

    @GetMapping(value = {"/newemployee"})
    public String getNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("tasks", Task.values());
        return "new_employee";
    }
    @PostMapping("/newemployee")
    public String addNewEmployee(Employee employee) {
        employeeService.save(employee);

        return "redirect:/employee";
    }

    @GetMapping("/payroll")
    public String getPayrollPage (Model model) {
        model.addAttribute("employees", employeeService.getEmployees());
        return "payroll";
    }

    @GetMapping("/january")
    public String getJanuaryPage (Model model) {
        model.addAttribute("employees", employeeService.getEmployees());
        return "january";
    }

}

package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.*;
import hu.progmatic.company_management_system.services.EmployeeService;
import hu.progmatic.company_management_system.services.MonthlyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final MonthlyDataService monthlyDataService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, MonthlyDataService monthlyDataService) {
        this.employeeService = employeeService;
        this.monthlyDataService = monthlyDataService;
    }

    @GetMapping("/employees")
    public String getEmployeePage(Model model) {
        List<Employee> employees = employeeService.getEmployees();

        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees/{taxnumber}")
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

        return "redirect:/employees";
    }

    @GetMapping("/payroll")
    public String getPayrollPage(Model model) {
        model.addAttribute("employees", employeeService.getEmployees());
        return "payroll";
    }

    @GetMapping("/netsalary")
    public String getNetSalaryPage(Model model) {

        List<Employee> employees = employeeService.getEmployees();

        model.addAttribute("employees", employees);

        monthlyDataService.getMonthlyData();
        return "netsalary";
    }

    @GetMapping("/netsalary/{taxnumber}")
    public String getOneEmployeeForNetSalary (@PathVariable String taxnumber, Model model) {
        Employee e1 = employeeService.getEmployeeByTaxNumber(taxnumber);
        model.addAttribute("oneEmployee", e1);
        return "netsalary";
    }

    @PostMapping("/netsalary")
    public String calculateNetSalary(Model model) {
        int netsalary = monthlyDataService.setNetSalary(500000,25,6,0,0);
        model.addAttribute("netsalary", netsalary);
        System.out.println(netsalary);
        return "netsalary";
    }


    @GetMapping("/form-handler")
    public String getNetSalary(Model model){

        return "netsalary";
    }


    @PostMapping("/form-handler")
    public String getOneNetSalary(Model model){

        return "netsalary";
    }



    @GetMapping("/workinghours")
    public String getWorkinghoursPage(Model model){
        Employee employees = employeeService.getEmployeeByTaxNumber("8369754421");
        model.addAttribute("emplo", employees);
        model.addAttribute("valami", new Employee());

        return "workinghours";
    }

    @PostMapping("/workinghours")
    public String getNewWorkingHours(Employee barmi) {

        System.out.println(barmi.getName());
        System.out.println(barmi.getWorkingHours());

        System.out.println(barmi.getWorkingHours()+2);
        return "redirect:/workinghours";
    }


    @GetMapping("/january")
    public String getJanuaryPage(Model model){
        List<Employee> employees = employeeService.getEmployees();

        model.addAttribute("employees", employees);
        return "january";
    }

    @GetMapping("/actualnetsalary/{taxnumber}")
    public String getActualNetSalaryPage(Model model, @PathVariable String taxnumber){
        Employee e1 = employeeService.getEmployeeByTaxNumber(taxnumber);
        model.addAttribute("e", e1);
        return "actualnetsalary";
    }

    @PostMapping("/actualnetsalary/{taxnumber}")
        public String getActualNetSalaryCalculation(Model model, @PathVariable String taxnumber,
                                                    @RequestParam  int workingDays, @RequestParam int paidLeave, @RequestParam int sickLeave,
                                                    @RequestParam int illnessBenefit){
        Employee e1 = employeeService.getEmployeeByTaxNumber(taxnumber);
        model.addAttribute("e", e1);
        int money = monthlyDataService.setNetSalary(e1.getGrossSalary(), workingDays, paidLeave, sickLeave, illnessBenefit);
        model.addAttribute("money", money);
        return "actualnetsalary";
    }

}

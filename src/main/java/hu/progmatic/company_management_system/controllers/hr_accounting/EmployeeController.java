package hu.progmatic.company_management_system.controllers.hr_accounting;

import hu.progmatic.company_management_system.models.*;
import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import hu.progmatic.company_management_system.models.hr_accounting.Month;
import hu.progmatic.company_management_system.models.hr_accounting.MonthlyData;
import hu.progmatic.company_management_system.models.hr_accounting.Year;
import hu.progmatic.company_management_system.services.EmployeeService;
import hu.progmatic.company_management_system.services.MonthlyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("tasks", Position.values());
        return "new_employee";
    }

    @PostMapping("/newemployee")
    public String addNewEmployee(Employee employee) {
        employeeService.save(employee);

        return "redirect:/employees";
    }



    @GetMapping("/netsalary")
    public String getNetSalaryPage(Model model) {

        List<Employee> employees = employeeService.getEmployees();

        model.addAttribute("employees", employees);

        List<MonthlyData> monthlyDataList = monthlyDataService.getMonthlyData();
        model.addAttribute("monthlyDataList", monthlyDataList);
        System.out.println(monthlyDataList);

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


    @GetMapping("/payroll")
    public String getJanuaryPage(Model model){
        List<Employee> employees = employeeService.getEmployees();

        model.addAttribute("employees", employees);
        return "payroll";
    }

    @GetMapping("/actualnetsalary/{taxnumber}")
    public String getActualNetSalaryPage(Model model, @PathVariable String taxnumber){
        Employee e1 = employeeService.getEmployeeByTaxNumber(taxnumber);
        MonthlyData monthlyData = new MonthlyData();

        model.addAttribute("monthlyData", monthlyData);
        model.addAttribute("e", e1);
        model.addAttribute("years", Year.values());
        model.addAttribute("monthes", Month.values());
        return "actualnetsalary";
    }

    @PostMapping("/actualnetsalary/{taxnumber}")
    public String getActualNetSalaryCalculation(@PathVariable String taxnumber, @ModelAttribute(name = "monthlyData") MonthlyData monthlyData, Model model){
        Employee e1 = employeeService.getEmployeeByTaxNumber(taxnumber);
        monthlyData.setEmployee(e1);
        monthlyData.setNetSalary(monthlyData.getWorkingDays(), monthlyData.getPaidLeave(), monthlyData.getSickLeave(), monthlyData.getIllnessBenefit());
        monthlyDataService.save(monthlyData);

        int money = monthlyData.getNetSalary();

        model.addAttribute("monthlyData", monthlyData);
        model.addAttribute("e", e1);
        model.addAttribute("money", money);

        return "actualnetsalary";
    }
    /*@PostMapping("/actualnetsalary/{taxnumber}")
        public String getActualNetSalaryCalculation(Model model, @PathVariable String taxnumber,
                                                    @RequestParam  int workingDays, @RequestParam int paidLeave, @RequestParam int sickLeave,
                                                    @RequestParam int illnessBenefit){
        Employee e1 = employeeService.getEmployeeByTaxNumber(taxnumber);
        model.addAttribute("e", e1);
        int money = monthlyDataService.setNetSalary(e1.getGrossSalary(), workingDays, paidLeave, sickLeave, illnessBenefit);
        model.addAttribute("money", money);
        return "actualnetsalary";
    }*/

}

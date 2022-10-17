package hu.progmatic.company_management_system.controllers.hr_accounting;

import hu.progmatic.company_management_system.models.*;
import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import hu.progmatic.company_management_system.models.hr_accounting.Month;
import hu.progmatic.company_management_system.models.hr_accounting.MonthlyData;
import hu.progmatic.company_management_system.models.hr_accounting.Year;
import hu.progmatic.company_management_system.searchform.EmployeeSearchForm;
import hu.progmatic.company_management_system.searchform.NetSalarySearchForm;
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

        //CSS-hez th:class
       model.addAttribute("selectedLocation", "Employees");
       model.addAttribute("form", new EmployeeSearchForm());
        return "employees";
    }

    @PostMapping("/employees")
    public String getEmployeeFromForm(EmployeeSearchForm form, Model model) {
        List<Employee> employees = employeeService.getByForm(form);

        model.addAttribute("employees", employees);
        model.addAttribute("form", form);

        return "employees";
    }

    @GetMapping("/employees/{taxnumber}")
    public String getOneEmployee(@PathVariable String taxnumber, Model model) {
        Employee e1 = employeeService.getEmployeeByTaxNumber(taxnumber);
        model.addAttribute("oneEmployee", e1);

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Employees");
        return "actualEmployee";
    }

    @GetMapping(value = {"/newemployee"})
    public String getNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("tasks", Position.values());

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Employees");
        return "new_employee";
    }

    @PostMapping("/newemployee")
    public String addNewEmployee(Employee employee) {
        employeeService.save(employee);

        return "redirect:/employees";
    }


    @GetMapping("/netsalary/{taxnumber}")
    public String getOneEmployeeForNetSalary (@PathVariable String taxnumber, Model model) {
        Employee e1 = employeeService.getEmployeeByTaxNumber(taxnumber);
        model.addAttribute("oneEmployee", e1);

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Netsalary");

        return "netsalary";
    }

    @GetMapping("/netsalary")
    public String getNetSalaryPage(Model model) {

        List<Employee> employees = employeeService.getEmployees();

        model.addAttribute("employees", employees);

        List<MonthlyData> monthlyDataList = monthlyDataService.getMonthlyData();
        model.addAttribute("monthlyDataList", monthlyDataList);
        System.out.println(monthlyDataList);

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Netsalary");
        model.addAttribute("form", new NetSalarySearchForm());
        model.addAttribute("years", Year.values());
        model.addAttribute("months", Month.values());
        return "netsalary";
    }

    @PostMapping("/netsalary")
    public String calculateNetSalary(NetSalarySearchForm form, Model model) {
       List<MonthlyData> monthlyDataList = monthlyDataService.getByForm(form);
       model.addAttribute("monthlyDataList", monthlyDataList);
       model.addAttribute("form", form);
       model.addAttribute("years", Year.values());
       model.addAttribute("months", Month.values());
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

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Payroll");
        return "payroll";
    }

    @GetMapping("/actualnetsalary/{taxnumber}")
    public String getActualNetSalaryPage(Model model, @PathVariable String taxnumber){
        Employee e1 = employeeService.getEmployeeByTaxNumber(taxnumber);
        MonthlyData monthlyData = new MonthlyData();

        model.addAttribute("monthlyData", monthlyData);
        model.addAttribute("e", e1);
        model.addAttribute("years", Year.values());
        model.addAttribute("months", Month.values());

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Payroll");
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

        model.addAttribute("selectedLocation", "Payroll");

        return "actualnetsalary";
    }

    @GetMapping("/useroptions")
    public String getEmployeeWithoutUser(Model model) {
        List<Employee> employees = employeeService.getEmployeeUserNull();
        model.addAttribute("employees", employees);
        List<Employee> allemployees = employeeService.getEmployeeUserNotNull();
        model.addAttribute("allemployees", allemployees);

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Useroptions");
        return "useroptions";
    }

    @GetMapping("/useroptions/{taxnumber}")
    public String getEmployeeWithoutUserOpt(@PathVariable String taxnumber, Model model) {
        Employee employee = employeeService.getEmployeeByTaxNumber(taxnumber);
        model.addAttribute("employee", employee);
        model.addAttribute("user", new User());
        model.addAttribute("taxnumber", taxnumber);

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Useroptions");
        return "saveUser";
    }

}

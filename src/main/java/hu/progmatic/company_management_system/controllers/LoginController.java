package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.Position;
import hu.progmatic.company_management_system.models.User;
import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import hu.progmatic.company_management_system.services.EmployeeService;
import hu.progmatic.company_management_system.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    private final UserService userService;
    private final EmployeeService employeeService;


    public LoginController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }


    @GetMapping(value = {"/", "/home", "/login"})
    public String loginPage() {
        if (userService.isAuthenticated()){
            return "redirect:/main";
        }

        return "login";
    }

    @GetMapping("/login-error")
    public String loginErrorPage(Model model) {
        model.addAttribute("loginError", true);

        return "login";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession(true).invalidate();

        return "redirect:/login";
    }


    @PostMapping(value = {"/register"})
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(name = "taxnumber") String taxnumber, @RequestParam(name = "position") Position position) {
        Employee e1 = employeeService.getEmployeeByTaxNumber(taxnumber);
        User user1 = userService.findEmployee(e1);
        if (user1 != null) {
            if (user1.getEmployee() != null) {
                user1.setEmployee(e1);
                user1.setUsername(user.getUsername());
                user1.setPassword(user.getPassword());
                user1.setPosition(user.getPosition());
                e1.setPersonnelTask(position);
                userService.saveUser(user1);
                employeeService.save(e1);
            }
        }else {
            user.setEmployee(e1);
            e1.setUser(user);
            e1.setPersonnelTask(position);
            userService.saveUser(user);
            employeeService.save(e1);
        }

        return "redirect:/useroptions";
    }

    @GetMapping(value = "/users")
    public String getAllUser(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "userlist";
    }
}

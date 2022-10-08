package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.User;
import hu.progmatic.company_management_system.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
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

    @GetMapping(value = {"/register"})
    public String saveUserPage(Model model) {
        model.addAttribute("user", new User());

        return "saveUser";
    }

    @PostMapping(value = {"/register"})
    public String saveUser(User user) {
        userService.saveUser(user);

        return "redirect:/login";
    }

    @GetMapping(value = "/users")
    public String getAllUser(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "userlist";
    }
}

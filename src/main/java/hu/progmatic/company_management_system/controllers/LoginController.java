package hu.progmatic.company_management_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = {"/", "/home", "/login"})
    public String getLoginPage() {
        return "login";
    }

}

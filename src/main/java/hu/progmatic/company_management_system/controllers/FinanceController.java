package hu.progmatic.company_management_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinanceController {

   @GetMapping("/finance")
    public String getFinancePage() {
       return "finance";
   }
}

package hu.progmatic.company_management_system.controllers.hr_accounting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinanceController {

   @GetMapping("/finance")
    public String getFinancePage(Model model) {

       //CSS-hez th:class
       model.addAttribute("selectedLocation", "Finance");
       return "finance";
   }
}

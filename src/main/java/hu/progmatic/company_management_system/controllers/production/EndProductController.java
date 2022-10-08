package hu.progmatic.company_management_system.controllers.production;

import hu.progmatic.company_management_system.models.production.EndProduct;
import hu.progmatic.company_management_system.searchform.EndProductSearchForm;
import hu.progmatic.company_management_system.services.EndProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EndProductController {

    private final EndProductService endProductService;

    public EndProductController(EndProductService endProductService) {
        this.endProductService = endProductService;
    }

    @GetMapping(value = {"/endproducts"})
    public String getEndProductsPage(Model model) {
        List<EndProduct> endProducts = endProductService.getAllEndProduct();
        model.addAttribute("endProducts", endProducts);
        model.addAttribute("page", "End Products");
        model.addAttribute("form", new EndProductSearchForm());
        return "endproducts";
    }

    @PostMapping("/endproducts")
    public String displayEndProductSearchResults(EndProductSearchForm form, Model model) {
        List<EndProduct> endProducts = endProductService.getByForm(form);
        model.addAttribute("endProducts", endProducts);
        model.addAttribute("page", "End Products");
        model.addAttribute("form", form);
        return "endproducts";
    }
}

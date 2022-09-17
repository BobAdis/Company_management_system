package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.BOMList;
import hu.progmatic.company_management_system.models.Ingredient;
import hu.progmatic.company_management_system.searchform.BOMListSearchForm;
import hu.progmatic.company_management_system.searchform.IngredientSearchForm;
import hu.progmatic.company_management_system.services.BOMListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BOMListController {

    private final BOMListService bomListService;

    public BOMListController(BOMListService bomListService) {
        this.bomListService = bomListService;
    }

    @GetMapping(value = {"/bomlists"})
    public String getBOMListsPage(Model model) {
        List<BOMList> bomlists = bomListService.getAllBOMList();
        model.addAttribute("bomlists", bomlists);
        model.addAttribute("page", "BOM lists");
        model.addAttribute("form", new BOMList());
        return "bomlists";
    }

    @PostMapping("/bomlists")
    public String displayShippingSearchResults(BOMListSearchForm form, Model model) {
        List<BOMList> bomlists = bomListService.getByForm(form);
        model.addAttribute("bomlists", bomlists);
        model.addAttribute("page", "BOM lists");
        model.addAttribute("form", form);
        return "bomlists";
    }
}

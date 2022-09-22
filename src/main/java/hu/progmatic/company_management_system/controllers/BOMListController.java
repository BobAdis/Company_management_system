package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.BOMList;
import hu.progmatic.company_management_system.models.Ingredient;
import hu.progmatic.company_management_system.models.ProducedProduct;
import hu.progmatic.company_management_system.searchform.BOMListSearchForm;
import hu.progmatic.company_management_system.searchform.IngredientSearchForm;
import hu.progmatic.company_management_system.services.BOMListService;
import hu.progmatic.company_management_system.services.IngredientService;
import hu.progmatic.company_management_system.services.ProducedProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BOMListController {

    private final BOMListService bomListService;
    private final ProducedProductService producedProductService;
    private final IngredientService ingredientService;

    public BOMListController(BOMListService bomListService, ProducedProductService producedProductService, IngredientService ingredientService) {
        this.bomListService = bomListService;
        this.producedProductService = producedProductService;
        this.ingredientService = ingredientService;
    }

    @GetMapping(value = {"/bomlists"})
    public String getBOMListsPage(Model model) {
        List<BOMList> bomlists = bomListService.getAllBOMList();
        model.addAttribute("bomlists", bomlists);
        model.addAttribute("page", "BOM lists");
        model.addAttribute("form", new BOMListSearchForm());
        return "bomlists";
    }

    @PostMapping("/bomlists")
    public String displayBomlistSearchResults(BOMListSearchForm form, Model model) {
        List<BOMList> bomlists = bomListService.getByForm(form);
        model.addAttribute("bomlists", bomlists);
        model.addAttribute("page", "BOM lists");
        model.addAttribute("form", form);
        return "bomlists";
    }

    @GetMapping(value = {"/newbomlist"})
    public String getNewBomListForm(Model model) {
        BOMList bomList = new BOMList();
        List<ProducedProduct> producedProducts = producedProductService.getAllProduct();
        //List<Ingredient> ingredients = ingredientService.getAllIngredient();

        model.addAttribute("bomlist", bomList);
        model.addAttribute("producedProducts", producedProducts);
        //model.addAttribute("ingredients", ingredients);
        model.addAttribute("productName", "BomList");

        return "new_bomlist";
    }

    @PostMapping(value = {"/newbomlist"})
    public String addNewBomList(BOMList bomList) {
        bomListService.saveBomList(bomList);

        return "redirect:/bomlists";
    }
}

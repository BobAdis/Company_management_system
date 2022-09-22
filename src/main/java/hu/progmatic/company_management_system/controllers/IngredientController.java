package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.BOMList;
import hu.progmatic.company_management_system.models.Ingredient;
import hu.progmatic.company_management_system.searchform.IngredientSearchForm;
import hu.progmatic.company_management_system.services.BOMListService;
import hu.progmatic.company_management_system.services.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class IngredientController {

    private final IngredientService ingredientService;
    private final BOMListService bomListService;

    public IngredientController(IngredientService ingredientService, BOMListService bomListService) {
        this.ingredientService = ingredientService;
        this.bomListService = bomListService;
    }


    @GetMapping(value = {"/ingredients"})
    public String getIngredientsPage(Model model) {
        List<Ingredient> ingredients = ingredientService.getAllIngredient();
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("page", "Ingredients");
        model.addAttribute("form", new Ingredient());
        return "ingredients";
    }

    @PostMapping("/ingredients")
    public String displayIngredientSearchResults(IngredientSearchForm form, Model model) {
        List<Ingredient> ingredients = ingredientService.getByForm(form);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("page", "Ingredients");
        model.addAttribute("form", form);
        return "ingredients";
    }

    @GetMapping(value = {"/newingredient"})
    public String getNewIngredientForm(Model model) {
        Ingredient ingredient = new Ingredient();
        List<BOMList> bomLists = bomListService.getAllBOMList();
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("bomlists", bomLists);
        model.addAttribute("productName", "Ingredient");

        return "new_ingredient";
    }

    @PostMapping(value = {"/newingredient"})
    public String addNewIngredient(Ingredient ingredient) {
        ingredientService.saveIngredient(ingredient);

        return "redirect:/ingredients";
    }
}

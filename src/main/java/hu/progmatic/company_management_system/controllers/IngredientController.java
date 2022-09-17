package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.Ingredient;
import hu.progmatic.company_management_system.searchform.IngredientSearchForm;
import hu.progmatic.company_management_system.services.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
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
}

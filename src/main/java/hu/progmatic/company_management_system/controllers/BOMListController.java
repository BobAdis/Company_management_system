package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.BOMList;
import hu.progmatic.company_management_system.models.Ingredient;
import hu.progmatic.company_management_system.models.ProducedProduct;
import hu.progmatic.company_management_system.searchform.BOMListSearchForm;
import hu.progmatic.company_management_system.services.BOMListService;
import hu.progmatic.company_management_system.services.IngredientService;
import hu.progmatic.company_management_system.services.ProducedProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<ProducedProduct> producedProducts = producedProductService.getWhereBomListIsNull();

        model.addAttribute("bomlist", bomList);
        model.addAttribute("producedProducts", producedProducts);
        model.addAttribute("productName", "BomList");

        return "new_bomlist";
    }

    @PostMapping(value = {"/newbomlist"})
    public String addNewBomList(BOMList bomList, @RequestParam(name = "productId") long productId) {
        ProducedProduct producedProduct = producedProductService.getById(productId);
        bomList.setProducedProduct(producedProduct);
        producedProduct.setBomList(bomList);

        bomListService.saveBomList(bomList);

        return "redirect:/addingredient/" + bomList.getId();
    }

    @GetMapping(value = {"/addingredient/{id}"})
    public String addIngredientsToBomlistForm(@PathVariable long id, Model model) {
        BOMList bomList = bomListService.getBOMListById(id);

        List<Ingredient> ingredients = ingredientService.getAllIngredient();

        model.addAttribute("bomlist", bomList);
        model.addAttribute("ingredients", ingredients);

        return "addingredientstobomlist";
    }

    @PostMapping(value = {"/addingredient"})
    public String addIngredientsToBomList(@RequestParam(name = "bomlistId") long bomlistId, @RequestParam(name = "ingredientId") long ingredientId) {
        BOMList bomList = bomListService.getBOMListById(bomlistId);

        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = ingredientService.getById(ingredientId);
        ingredients.add(ingredient);

        bomList.setIngredients(ingredients);
        ingredient.setBomList(bomList);

        bomListService.saveBomList(bomList);

        return "redirect:/addingredient/" + bomList.getId();
    }
}

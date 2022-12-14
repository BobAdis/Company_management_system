package hu.progmatic.company_management_system.controllers.sales;

import hu.progmatic.company_management_system.models.production.Ingredient;
import hu.progmatic.company_management_system.models.production.RawMaterial;
import hu.progmatic.company_management_system.models.production.Warehouse;
import hu.progmatic.company_management_system.models.sales.Partner;
import hu.progmatic.company_management_system.models.sales.ShippingIn;
import hu.progmatic.company_management_system.searchform.ShippingInSearchForm;
import hu.progmatic.company_management_system.services.IngredientService;
import hu.progmatic.company_management_system.services.PartnerService;
import hu.progmatic.company_management_system.services.RawMaterialService;
import hu.progmatic.company_management_system.services.ShippingInService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShippingInController {

    private final ShippingInService shippingInService;
    private final PartnerService partnerService;

    private final RawMaterialService rawMaterialService;
    private final IngredientService ingredientService;

    public ShippingInController(ShippingInService shippingInService, PartnerService partnerService, RawMaterialService rawMaterialService, IngredientService ingredientService) {
        this.shippingInService = shippingInService;
        this.partnerService = partnerService;
        this.rawMaterialService = rawMaterialService;
        this.ingredientService = ingredientService;
    }

    @GetMapping(value = {"/shippingins"})
    public String getShippingInsPage(Model model) {
        List<ShippingIn> shippingIns = shippingInService.getAllShippingIn();
        model.addAttribute("shippingIns", shippingIns);
        model.addAttribute("page", "Shipping Ins");
        model.addAttribute("form", new ShippingInSearchForm());

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Shippingins");
        return "shippingins";
    }

    @PostMapping("/shippingins")
    public String displayShippingInSearchResults(ShippingInSearchForm form, Model model) {
        List<ShippingIn> shippingIns = shippingInService.getByForm(form);
        model.addAttribute("shippingIns", shippingIns);
        model.addAttribute("page", "Shipping Ins");
        model.addAttribute("form", form);
        return "shippingins";
    }

    @GetMapping(value = {"/newshippingin"})
    public String getNewShippingInForm(Model model) {
        ShippingIn shippingIn = new ShippingIn();
        List<Partner> partners = partnerService.getAllSupplier();

        model.addAttribute("shippingin", shippingIn);
        model.addAttribute("partners", partners);
        model.addAttribute("productName", "Shipping in");

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Shippingins");

        return "new_shippingin";
    }

    @PostMapping(value = {"/newshippingin"})
    public String addShippingInList(ShippingIn shippingIn, @RequestParam(name = "partnerId") long partnerId) {
        Partner partner = partnerService.getSupplierById(partnerId);
        shippingIn.setSeller(partner);
        shippingInService.saveShippingIn(shippingIn);

        return "redirect:/addrawmaterial/" + shippingIn.getId();
    }

    @GetMapping(value = {"/addrawmaterial/{id}"})
    public String addRawMaterialsToShippingInForm(@PathVariable long id, Model model) {
        ShippingIn shippingIn = shippingInService.getById(id);

        List<Ingredient> ingredients = ingredientService.getAllIngredient();

        model.addAttribute("shippingIn", shippingIn);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("rawMaterial", new RawMaterial());

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Shippingins");

        return "addrawmaterialstoshippingin";
    }

    @PostMapping(value = {"/addrawmaterial"})
    public String addRawMaterialsToShippingIn(@RequestParam(name = "shippingInId") long shippingInId,
                                              @ModelAttribute("rawMaterial") RawMaterial rawMaterial,
                                              @RequestParam(name = "ingredientId") long ingredientId) {
        ShippingIn shippingIn = shippingInService.getById(shippingInId);
        Ingredient ingredient = ingredientService.getById(ingredientId);

        rawMaterial.setIngredient(ingredient);
        rawMaterial.setWarehouse(Warehouse.INBOUND);

        if(shippingIn.getRawMaterials().size() != 0) {
            List<RawMaterial> rawMaterials = shippingIn.getRawMaterials();
            rawMaterials.add(rawMaterial);
            shippingIn.setRawMaterials(rawMaterials);

            rawMaterial.setShippingIn(shippingIn);
            rawMaterialService.save(rawMaterial);
        } else {
            shippingIn.setRawMaterials(List.of(rawMaterial));

            rawMaterial.setShippingIn(shippingIn);
            rawMaterialService.save(rawMaterial);
        }

        return "redirect:/addrawmaterial/" + shippingIn.getId();
    }
}

package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.RawMaterial;
import hu.progmatic.company_management_system.models.Warehouse;
import hu.progmatic.company_management_system.searchform.RawMaterialSearchForm;
import hu.progmatic.company_management_system.services.RawMaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }


    @GetMapping(value = {"/rawmaterials"})
    public String getRawmaterialsPage(Model model) {
        List<RawMaterial> rawmaterials = rawMaterialService.getAllRawMaterial();
        model.addAttribute("rawMaterials", rawmaterials);
        model.addAttribute("page", "Raw materials");
        model.addAttribute("form", new RawMaterial());
        return "rawmaterials";
    }

    @PostMapping("/rawmaterials")
    public String displayRawSearchResults(RawMaterialSearchForm form, Model model) {
        List<RawMaterial> rawMaterials = rawMaterialService.getByForm(form);
        model.addAttribute("rawMaterials", rawMaterials);
        model.addAttribute("page", "Raw materials");
        model.addAttribute("form", form);
        return "rawmaterials";
    }

    @GetMapping(value = {"/transfer"})
    public String getTransferPage(Model model) {
        List<RawMaterial> rawmaterials = rawMaterinalService.getAllRawMaterial();
        model.addAttribute("rawMaterials", rawmaterials);
        model.addAttribute("page", "Transfer page");
        model.addAttribute("warehouses", Warehouse.values());
        return "transfer";
    }

    @PostMapping (value = {"/transfer"})
    public String postTransferPage(Model model, @RequestParam Integer sarzsN, @RequestParam int quantity, @RequestParam Warehouse warehouseout, @RequestParam Warehouse warehousein) {
        rawMaterinalService.transferData(sarzsN, quantity, warehouseout, warehousein);
        return "redirect:/transfer";
    }
}

package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.RawMaterial;
import hu.progmatic.company_management_system.models.Warehouse;
import hu.progmatic.company_management_system.services.RawMaterinalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WarehousesController {

    private final RawMaterinalService rawMaterinalService;

    public WarehousesController(RawMaterinalService rawMaterinalService) {
        this.rawMaterinalService = rawMaterinalService;
    }


    @GetMapping(value = {"/warehouses"})
    public String getWarehousesPage(Model model) {
        model.addAttribute("warehouses", Warehouse.values());
        return "warehouses";
    }

    @PostMapping("/warehouses")
    public String getWarehousePage(@RequestParam(name = "warehouse") Warehouse warehouse, Model model) {
        List<RawMaterial> rawMaterials = rawMaterinalService.getAllRawMaterialbySARZSbyWarehouse(warehouse);
        model.addAttribute("warehouses", Warehouse.values());
        model.addAttribute("rawMaterials", rawMaterials);
        model.addAttribute("warehouse", warehouse);
        return "warehouses";
    }


}

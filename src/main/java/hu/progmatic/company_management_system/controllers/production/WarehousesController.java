package hu.progmatic.company_management_system.controllers.production;

import hu.progmatic.company_management_system.models.production.RawMaterial;
import hu.progmatic.company_management_system.models.production.Warehouse;
import hu.progmatic.company_management_system.services.RawMaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WarehousesController {

    private final RawMaterialService rawMaterialService;

    public WarehousesController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }


    @GetMapping(value = {"/warehouses"})
    public String getWarehousesPage(Model model) {
        List<RawMaterial> inbound = rawMaterialService.getRawMaterialByWarehouse(Warehouse.INBOUND);
        List<RawMaterial> outbound = rawMaterialService.getRawMaterialByWarehouse(Warehouse.OUTBOUND);
        List<RawMaterial> workstations = rawMaterialService.getRawMaterialByWarehouse(Warehouse.WORKSTATIONS);
        List<RawMaterial> reject = rawMaterialService.getRawMaterialByWarehouse(Warehouse.REJECT);

        model.addAttribute("warehouses", Warehouse.values());

        model.addAttribute("inbound", inbound);
        model.addAttribute("outbound", outbound);
        model.addAttribute("workstations", workstations);
        model.addAttribute("reject", reject);

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Warehouses");
        return "warehouses";
    }

    @PostMapping("/warehouses")
    public String getWarehousePage(@RequestParam(name = "warehouse") Warehouse warehouse, Model model) {
        List<RawMaterial> rawMaterials = rawMaterialService.getAllRawMaterialbySARZSbyWarehouse(warehouse);

        List<RawMaterial> inbound = rawMaterialService.getRawMaterialByWarehouse(Warehouse.INBOUND);
        List<RawMaterial> outbound = rawMaterialService.getRawMaterialByWarehouse(Warehouse.OUTBOUND);
        List<RawMaterial> workstations = rawMaterialService.getRawMaterialByWarehouse(Warehouse.WORKSTATIONS);
        List<RawMaterial> reject = rawMaterialService.getRawMaterialByWarehouse(Warehouse.REJECT);

        model.addAttribute("inbound", inbound);
        model.addAttribute("outbound", outbound);
        model.addAttribute("workstations", workstations);
        model.addAttribute("reject", reject);

        model.addAttribute("warehouses", Warehouse.values());
        model.addAttribute("rawMaterials", rawMaterials);
        model.addAttribute("warehouse", warehouse);

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Warehouses");
        return "warehouses";
    }


}

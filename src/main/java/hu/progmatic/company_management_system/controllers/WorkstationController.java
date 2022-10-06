package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.BOMList;
import hu.progmatic.company_management_system.models.EndProduct;
import hu.progmatic.company_management_system.models.RawMaterial;
import hu.progmatic.company_management_system.services.BOMListService;
import hu.progmatic.company_management_system.services.EndProductService;
import hu.progmatic.company_management_system.services.RawMaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WorkstationController {

    private final BOMListService bomListService;

    private final RawMaterialService rawMaterialService;

    private final EndProductService endProductService;

    public WorkstationController(BOMListService bomListService, RawMaterialService rawMaterialService, EndProductService endProductService) {
        this.bomListService = bomListService;
        this.rawMaterialService = rawMaterialService;
        this.endProductService = endProductService;
    }

    @GetMapping("/workstation")
    public String getWorkstation(Model model) {

        List<BOMList> bomLists = bomListService.getAllBOMList();

        model.addAttribute("bomlists", bomLists);

        return "workstation";
    }

    @GetMapping("/workstation/{id}")
    public String getWorkstationBomListId(Model model, @PathVariable Long id) {

        BOMList bomList = bomListService.getBOMListById(id);

        List<RawMaterial> rawMaterials = rawMaterialService.getAllRawMaterial();
        List<EndProduct> endProducts = endProductService.getAllEndProduct();

        model.addAttribute("bomlist", bomList);
        model.addAttribute("rawmaterials", rawMaterials);
        model.addAttribute("endproducts", endProducts);

        return "work";
    }

    @PostMapping("/rm_workstation/{id}")
    public String subtractRawMaterialWorkstation(Model model, @PathVariable Long id, @RequestParam(name = "rm_id", required = false) Long rm_id,
                                  @RequestParam(name = "quantity") int quantity) {

        BOMList bomList = bomListService.getBOMListById(id);

        RawMaterial rawMaterial = rawMaterialService.getById(rm_id);

        boolean isSuccess = rawMaterialService.subtractRawMaterial(rm_id, quantity);

        List<BOMList> bomLists = bomListService.getAllBOMList();
        List<RawMaterial> rawMaterials = rawMaterialService.getAllRawMaterial();
        List<EndProduct> endProducts = endProductService.getAllEndProduct();

        model.addAttribute("rm_success", isSuccess);
        model.addAttribute("rawmaterial", rawMaterial);
        model.addAttribute("bomlist", bomList);
        model.addAttribute("bomlists", bomLists);
        model.addAttribute("rawmaterials", rawMaterials);
        model.addAttribute("endproducts", endProducts);

        return "work";
    }

    @PostMapping("/ep_workstation/{id}")
    public String addEndproductWorkstation(Model model, @PathVariable Long id, @RequestParam(name = "ep_id", required = false) Long ep_id,
                                  @RequestParam(name = "quantity") int quantity) {

        BOMList bomList = bomListService.getBOMListById(id);

        boolean isSuccess = endProductService.addEndproduct(ep_id, quantity);

        List<BOMList> bomLists = bomListService.getAllBOMList();
        List<RawMaterial> rawMaterials = rawMaterialService.getAllRawMaterial();
        List<EndProduct> endProducts = endProductService.getAllEndProduct();

        model.addAttribute("ep_success", isSuccess);
        model.addAttribute("bomlist", bomList);
        model.addAttribute("bomlists", bomLists);
        model.addAttribute("rawmaterials", rawMaterials);
        model.addAttribute("endproducts", endProducts);

        return "work";
    }

}

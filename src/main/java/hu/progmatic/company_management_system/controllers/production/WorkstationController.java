package hu.progmatic.company_management_system.controllers.production;

import hu.progmatic.company_management_system.models.production.BOMList;
import hu.progmatic.company_management_system.models.production.EndProduct;
import hu.progmatic.company_management_system.models.production.ProducedProduct;
import hu.progmatic.company_management_system.models.production.RawMaterial;
import hu.progmatic.company_management_system.services.BOMListService;
import hu.progmatic.company_management_system.services.EndProductService;
import hu.progmatic.company_management_system.services.ProducedProductService;
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

    private final ProducedProductService producedProductService;

    public WorkstationController(BOMListService bomListService, RawMaterialService rawMaterialService, EndProductService endProductService, ProducedProductService producedProductService) {
        this.bomListService = bomListService;
        this.rawMaterialService = rawMaterialService;
        this.endProductService = endProductService;
        this.producedProductService = producedProductService;
    }

    @GetMapping("/workstation")
    public String getWorkstation(Model model) {

        List<BOMList> bomLists = bomListService.getAllBOMList();

        model.addAttribute("bomlists", bomLists);

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Workstation");

        return "workstation";
    }

    @GetMapping("/workstation/{id}")
    public String getWorkstationBomListId(Model model, @PathVariable Long id) {

        BOMList bomList = bomListService.getBOMListById(id);

        List<RawMaterial> rawMaterials = rawMaterialService.getRawMaterialWhereWarehouseIsWorkstation();

        model.addAttribute("bomlist", bomList);
        model.addAttribute("rawmaterials", rawMaterials);
        model.addAttribute("producedProduct", bomList.getProducedProduct());
        model.addAttribute("endproduct", new EndProduct());

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Workstation");

        return "work";
    }

    @PostMapping("/rm_workstation/{id}")
    public String subtractRawMaterialWorkstation(Model model, @PathVariable Long id, @RequestParam(name = "rm_id", required = false) Long rm_id,
                                  @RequestParam(name = "quantity") int quantity) {

        BOMList bomList = bomListService.getBOMListById(id);

        RawMaterial rawMaterial = rawMaterialService.getById(rm_id);

        boolean isSuccess = rawMaterialService.subtractRawMaterial(rm_id, quantity);

        List<BOMList> bomLists = bomListService.getAllBOMList();
        List<RawMaterial> rawMaterials = rawMaterialService.getRawMaterialWhereWarehouseIsWorkstation();

        model.addAttribute("rm_success", isSuccess);
        model.addAttribute("rawmaterial", rawMaterial);
        model.addAttribute("bomlist", bomList);
        model.addAttribute("bomlists", bomLists);
        model.addAttribute("rawmaterials", rawMaterials);
        model.addAttribute("producedProduct", bomList.getProducedProduct());
        model.addAttribute("endproduct", new EndProduct());

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Workstation");

        return "work";
    }

    @PostMapping("/ep_workstation/{id}")
    public String addEndproductWorkstation(Model model, @PathVariable Long id,
                                           @ModelAttribute("endproduct") EndProduct endProduct,
                                           @RequestParam(name = "quantity") int quantity) {

        BOMList bomList = bomListService.getBOMListById(id);

        endProduct.setProducedProduct(bomList.getProducedProduct());
        endProduct.setQuantity(quantity);
        endProductService.save(endProduct);

        boolean isSuccess = true;

        List<BOMList> bomLists = bomListService.getAllBOMList();
        List<RawMaterial> rawMaterials = rawMaterialService.getRawMaterialWhereWarehouseIsWorkstation();

        model.addAttribute("ep_success", isSuccess);
        model.addAttribute("bomlist", bomList);
        model.addAttribute("bomlists", bomLists);
        model.addAttribute("rawmaterials", rawMaterials);
        model.addAttribute("producedProduct", bomList.getProducedProduct());

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Workstation");

        return "redirect:/workstation";
    }

}

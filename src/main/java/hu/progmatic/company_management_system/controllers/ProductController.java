package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.Product;
import hu.progmatic.company_management_system.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    /*@GetMapping(value = {"/rawmaterials"})
    public String saveNewSpaceShip(Model model) {
        model.addAttribute("newShip", new SpaceShip());
        model.addAttribute("shipClasses", SpaceShipClass.values());
        return "products";
    }*/
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(value = {"/finishedproducts"})
    public String getFinishedProductsPage(Model model) {
        List<Product> finished = productService.getAllFinishedProducts();
        model.addAttribute("products", finished);
        model.addAttribute("page", "Finished Products");
        return "products";
    }

    @GetMapping(value = {"/rawmaterials"})
    public String getRawMaterialsPage(Model model) {
        List<Product> raws = productService.getAllRawMaterialProducts();
        model.addAttribute("products", raws);
        model.addAttribute("page", "Raw Materials");
        return "products";
    }

    @GetMapping(value = {"/inboundwarehouse"})
    public String getInboundWarehousePage(Model model) {
        List<Product> inbound = productService.getAllInboundWarehouseProducts();
        model.addAttribute("products", inbound);
        model.addAttribute("page", "Inbound Warehouse");
        return "products";
    }

    @GetMapping(value = {"/outboundwarehouse"})
    public String getOutboundWarehousePage(Model model) {
        List<Product> outbound = productService.getAllOutboundWarehouseProducts();
        model.addAttribute("products", outbound);
        model.addAttribute("page", "Outbound Warehouse");
        return "products";
    }

    @GetMapping(value = {"/workstationstorage"})
    public String getWorkstationStoragePage(Model model) {
        List<Product> workstation = productService.getAllWorkstationStorageProducts();
        model.addAttribute("products", workstation);
        model.addAttribute("page", "Workstation Storage");
        return "products";
    }

    @GetMapping(value = {"/rejectwarehouse"})
    public String getRejectWarehouse(Model model) {
        List<Product> reject = productService.getAllRejectWarehouseProducts();
        model.addAttribute("products", reject);
        model.addAttribute("page", "Reject Warehouse");
        return "products";
    }

}
package hu.progmatic.company_management_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    /*@GetMapping(value = {"/rawmaterials"})
    public String saveNewSpaceShip(Model model) {
        model.addAttribute("newShip", new SpaceShip());
        model.addAttribute("shipClasses", SpaceShipClass.values());
        model.addAttribute("shipClasses", SpaceShipClass.values());

        return "products";
    }*/



    @GetMapping(value = {"/rawmaterials"})
    public String getRawMaterialsPage() {
        return "rawmaterials";
    }

    @GetMapping(value = {"/products"})
    public String getProductsPage() {
        return "products";
    }

    @GetMapping(value = {"/inboundwarehouse"})
    public String getInboundWarehousePage() {
        return "inboundwarehouse";
    }

    @GetMapping(value = {"/outboundwarehouse"})
    public String getOutboundWarehousePage() {
        return "outboundwarehouse";
    }

    @GetMapping(value = {"/workstationstorage"})
    public String getWorkstationStoragePage() {
        return "workstationstorage";
    }

    @GetMapping(value = {"/rejectwarehouse"})
    public String getRejectWarehouse() {
        return "rejectwarehouse";
    }

}
package hu.progmatic.company_management_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartnerController {

    /*@GetMapping(value = {"/rawmaterials"})
    public String saveNewSpaceShip(Model model) {
        model.addAttribute("newShip", new SpaceShip());
        model.addAttribute("shipClasses", SpaceShipClass.values());
        model.addAttribute("shipClasses", SpaceShipClass.values());

        return "products";
    }*/

    @GetMapping(value = {"/customers"})
    public String getCustomersPage() {
        return "customers";
    }

    @GetMapping(value = {"/suppliers"})
    public String getSuppliersPage() {
        return "suppliers";
    }
}

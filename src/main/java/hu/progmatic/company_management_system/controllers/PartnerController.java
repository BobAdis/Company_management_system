package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.Partner;
import hu.progmatic.company_management_system.services.PartnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PartnerController {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }


    /*@GetMapping(value = {"/rawmaterials"})
    public String saveNewSpaceShip(Model model) {
        model.addAttribute("newShip", new SpaceShip());
        model.addAttribute("shipClasses", SpaceShipClass.values());
        model.addAttribute("shipClasses", SpaceShipClass.values());

        return "products";
    }*/

    @GetMapping(value = {"/suppliers"})
    public String getSuppliersPage(Model model) {
        List<Partner> suppliers = partnerService.getAllSupplier();
        model.addAttribute("partners", suppliers);
        model.addAttribute("page", "Suppliers List");
        return "partner";
    }

    @GetMapping(value = {"/customers"})
    public String getCustomersPage(Model model) {
        List<Partner> customers = partnerService.getAllCustomers();
        model.addAttribute("partners", customers);
        model.addAttribute("page", "Customer List");
        return "partner";
    }
}

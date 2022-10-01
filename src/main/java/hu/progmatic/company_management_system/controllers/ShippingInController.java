package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.Partner;
import hu.progmatic.company_management_system.models.RawMaterial;
import hu.progmatic.company_management_system.models.ShippingIn;
import hu.progmatic.company_management_system.searchform.ShippingInSearchForm;
import hu.progmatic.company_management_system.services.PartnerService;
import hu.progmatic.company_management_system.services.ShippingInService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShippingInController {

    private final ShippingInService shippingInService;
    private final PartnerService partnerService;

    public ShippingInController(ShippingInService shippingInService, PartnerService partnerService) {
        this.shippingInService = shippingInService;
        this.partnerService = partnerService;
    }

    @GetMapping(value = {"/shippingins"})
    public String getShippingInsPage(Model model) {
        List<ShippingIn> shippingIns = shippingInService.getAllShippingIn();
        model.addAttribute("shippingIns", shippingIns);
        model.addAttribute("page", "Shipping Ins");
        model.addAttribute("form", new ShippingInSearchForm());
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

        return "new_shippingin";
    }

    @PostMapping(value = {"/newshippingin"})
    public String addShippingInList(ShippingIn shippingIn, @RequestParam(name = "partnerId") long partnerId, RawMaterial rawMaterial) {
        Partner partner = partnerService.getSupplierById(partnerId);
        shippingIn.setSeller(partner);
        shippingInService.saveShippingIn(shippingIn);
        //shippingInService.saveNewShippingIn(rawMaterial);
        return "redirect:/shippingins";
    }
}

package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.ShippingIn;
import hu.progmatic.company_management_system.models.ShippingOut;
import hu.progmatic.company_management_system.searchform.ShippingInSearchForm;
import hu.progmatic.company_management_system.searchform.ShippingOutSearchForm;
import hu.progmatic.company_management_system.services.ShippingOutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShippingOutController {

    private final ShippingOutService shippingOutService;

    public ShippingOutController(ShippingOutService shippingOutService) {
        this.shippingOutService = shippingOutService;
    }

    @GetMapping(value = {"/shippingouts"})
    public String getShippingOutsPage(Model model) {
        List<ShippingOut> shippingOuts = shippingOutService.getAllShippingOut();
        model.addAttribute("shippingOuts", shippingOuts);
        model.addAttribute("page", "Shipping Outs");
        model.addAttribute("form", new ShippingOutSearchForm());
        return "shippingouts";
    }

    @PostMapping("/shippingouts")
    public String displayShippingOutSearchResults(ShippingOutSearchForm form, Model model) {
        List<ShippingOut> shippingOuts = shippingOutService.getByForm(form);
        model.addAttribute("shippingOuts", shippingOuts);
        model.addAttribute("page", "Shipping Outs");
        model.addAttribute("form", form);
        return "shippingouts";
    }

    @GetMapping(value = {"/newshippingout"})
    public String getNewBomListForm(Model model) {
        ShippingOut shippingOut = new ShippingOut();

        model.addAttribute("shippingout", shippingOut);
        model.addAttribute("productName", "Shipping out");

        return "new_shippingout";
    }

    @PostMapping(value = {"/newshippingout"})
    public String addNewBomList(ShippingOut shippingOut) {
        shippingOutService.saveShippingOut(shippingOut);

        return "redirect:/shippingouts";
    }
}

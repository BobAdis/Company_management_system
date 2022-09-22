package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.BOMList;
import hu.progmatic.company_management_system.models.ShippingIn;
import hu.progmatic.company_management_system.searchform.ShippingInSearchForm;
import hu.progmatic.company_management_system.services.ShippingInService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShippingInController {

    private final ShippingInService shippingInService;

    public ShippingInController(ShippingInService shippingInService) {
        this.shippingInService = shippingInService;
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
    public String getNewBomListForm(Model model) {
        ShippingIn shippingIn = new ShippingIn();

        model.addAttribute("shippingin", shippingIn);
        model.addAttribute("productName", "Shipping in");

        return "new_shippingin";
    }

    @PostMapping(value = {"/newshippingin"})
    public String addNewBomList(ShippingIn shippingIn) {
        shippingInService.saveShippingIn(shippingIn);

        return "redirect:/shippingins";
    }
}

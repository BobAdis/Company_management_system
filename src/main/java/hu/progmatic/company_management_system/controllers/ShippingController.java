package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.BOMList;
import hu.progmatic.company_management_system.models.Shipping;
import hu.progmatic.company_management_system.searchform.BOMListSearchForm;
import hu.progmatic.company_management_system.searchform.ShippnigSearchForm;
import hu.progmatic.company_management_system.services.ShippingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @GetMapping(value = {"/shippings"})
    public String getShippingsPage(Model model) {
        List<Shipping> shippings = shippingService.getAllShipping();
        model.addAttribute("shippings", shippings);
        model.addAttribute("page", "Shippings");
        model.addAttribute("form", new Shipping());
        return "shippings";
    }

    @PostMapping("/shippings")
    public String displayShippingSearchResults(ShippnigSearchForm form, Model model) {
        List<Shipping> shippings = shippingService.getByForm(form);
        model.addAttribute("shippings", shippings);
        model.addAttribute("page", "Shippings");
        model.addAttribute("form", form);
        return "shippings";
    }
}

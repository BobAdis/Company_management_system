package hu.progmatic.company_management_system.controllers.sales;

import hu.progmatic.company_management_system.models.production.EndProduct;
import hu.progmatic.company_management_system.models.sales.Partner;
import hu.progmatic.company_management_system.models.sales.ShippingOut;
import hu.progmatic.company_management_system.searchform.ShippingOutSearchForm;
import hu.progmatic.company_management_system.services.EndProductService;
import hu.progmatic.company_management_system.services.PartnerService;
import hu.progmatic.company_management_system.services.ShippingOutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShippingOutController {

    private final ShippingOutService shippingOutService;
    private final PartnerService partnerService;

    private final EndProductService endProductService;

    public ShippingOutController(ShippingOutService shippingOutService, PartnerService partnerService, EndProductService endProductService) {
        this.shippingOutService = shippingOutService;
        this.partnerService = partnerService;
        this.endProductService = endProductService;
    }

    @GetMapping(value = {"/shippingouts"})
    public String getShippingOutsPage(Model model) {
        List<ShippingOut> shippingOuts = shippingOutService.getAllShippingOut();
        model.addAttribute("shippingOuts", shippingOuts);
        model.addAttribute("page", "Shipping Outs");
        model.addAttribute("form", new ShippingOutSearchForm());

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Shippingouts");
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
    public String getNewShippingOutForm(Model model) {
        ShippingOut shippingOut = new ShippingOut();
        List<Partner> partners = partnerService.getAllCustomers();

        model.addAttribute("shippingout", shippingOut);
        model.addAttribute("partners", partners);
        model.addAttribute("productName", "Shipping out");

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Shippingouts");

        return "new_shippingout";
    }

    @PostMapping(value = {"/newshippingout"})
    public String addNewShippingOut(ShippingOut shippingOut, @RequestParam(name = "partnerId") long partnerId) {
        Partner partner = partnerService.getCustomerById(partnerId);
        shippingOut.setBuyer(partner);
        shippingOutService.saveShippingOut(shippingOut);

        return "redirect:/addendproduct/" + shippingOut.getId();
    }

    @GetMapping(value = {"/addendproduct/{id}"})
    public String addEndProductToShippingOutForm(@PathVariable long id, Model model) {
        ShippingOut shippingOut = shippingOutService.getById(id);

        List<EndProduct> endProducts = endProductService.getEndProductWhereShippingOutIsNull();

        model.addAttribute("shippingOut", shippingOut);
        model.addAttribute("endProducts", endProducts);

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Shippingouts");

        return "addendproductstoshippingout";
    }

    @PostMapping(value = {"/addendproduct"})
    public String addEndProductsToShippingOut(@RequestParam(name = "shippingOutId") long shippingOutId, @RequestParam(name = "endproductId") long endproductId) {
        ShippingOut shippingOut = shippingOutService.getById(shippingOutId);

        List<EndProduct> endProducts = new ArrayList<>();
        EndProduct endProduct = endProductService.getById(endproductId);
        endProducts.add(endProduct);

        shippingOut.setEndProducts(endProducts);
        endProduct.setShippingOut(shippingOut);

        shippingOutService.saveShippingOut(shippingOut);

        return "redirect:/addrawmaterial/" + shippingOut.getId();
    }
}

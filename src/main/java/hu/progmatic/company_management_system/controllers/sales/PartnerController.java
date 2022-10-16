package hu.progmatic.company_management_system.controllers.sales;

import hu.progmatic.company_management_system.models.Position;
import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import hu.progmatic.company_management_system.models.sales.Partner;
import hu.progmatic.company_management_system.models.sales.PartnerType;
import hu.progmatic.company_management_system.searchform.PartnerSearchForm;
import hu.progmatic.company_management_system.services.PartnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PartnerController {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping(value = {"/suppliers"})
    public String getSuppliersPage(Model model) {
        List<Partner> suppliers = partnerService.getAllSupplier();
        model.addAttribute("partners", suppliers);
        model.addAttribute("page", "Suppliers List");
        model.addAttribute("form", new PartnerSearchForm());

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Suppliers");
        return "suppliers";
    }

    @PostMapping("/suppliers")
    public String displaySupplierSearchResults(PartnerSearchForm form, Model model) {
        PartnerType partnerType = PartnerType.SUPPLIER;
        List<Partner> partners = partnerService.getByForm(form, partnerType);
        model.addAttribute("partners", partners);
        model.addAttribute("page", "Suppliers List");
        model.addAttribute("form", form);
        return "suppliers";
    }

    @GetMapping(value = {"/customers"})
    public String getCustomersPage(Model model) {
        List<Partner> customers = partnerService.getAllCustomers();
        model.addAttribute("partners", customers);
        model.addAttribute("page", "Customer List");
        model.addAttribute("form", new PartnerSearchForm());

        //CSS-hez th:class
        model.addAttribute("selectedLocation", "Customers");
        return "customers";
    }

    @PostMapping("/customers")
    public String displayCustomersSearchResults(PartnerSearchForm form, Model model) {
        PartnerType partnerType = PartnerType.CUSTOMER;
        List<Partner> partners = partnerService.getByForm(form, partnerType);
        model.addAttribute("partners", partners);
        model.addAttribute("page", "Customer List");
        model.addAttribute("form", form);
        return "customers";
    }

    @GetMapping(value = {"/newsupplier"})
    public String getNewSupplierForm(Model model) {
        Partner partner = new Partner();
        model.addAttribute("partner", partner);
        model.addAttribute("partnerType", PartnerType.SUPPLIER);

        return "newsupplier";
    }
    @PostMapping("/newsupplier")
    public String addNewSupplier(Partner partner) {
        partnerService.save(partner);

        return "redirect:/suppliers";
    }

    @GetMapping(value = {"/newcustomer"})
    public String getNewCustomerForm(Model model) {
        Partner partner = new Partner();
        model.addAttribute("partner", partner);
        model.addAttribute("partnerType", PartnerType.CUSTOMER);

        return "newcustomer";
    }
    @PostMapping("/newcustomer")
    public String addNewCustomer(Partner partner) {
        partnerService.save(partner);

        return "redirect:/customers";
    }



}

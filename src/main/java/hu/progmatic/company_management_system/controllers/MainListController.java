package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.BOMList;
import hu.progmatic.company_management_system.models.Partner;
import hu.progmatic.company_management_system.models.ProducedProduct;
import hu.progmatic.company_management_system.repositories.ProducedProductRepo;
import hu.progmatic.company_management_system.searchform.BOMListSearchForm;
import hu.progmatic.company_management_system.searchform.ProducedProductSearchForm;
import hu.progmatic.company_management_system.services.BOMListService;
import hu.progmatic.company_management_system.services.PartnerService;
import hu.progmatic.company_management_system.services.ProducedProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainListController {

    private final BOMListService bomListService;
    private final PartnerService partnerService;

    private final ProducedProductService producedProductService;

    public MainListController(BOMListService bomListService, PartnerService partnerService, ProducedProductService producedProductService) {
        this.bomListService = bomListService;
        this.partnerService = partnerService;
        this.producedProductService = producedProductService;
    }

    @GetMapping(value = {"/main"})
    public String getMainPage(Model model) {
        List<BOMList> bomlists = bomListService.getAllBOMList();
        model.addAttribute("bomlists", bomlists);
        model.addAttribute("page", "BOM lists");
        model.addAttribute("form", new BOMListSearchForm());

        List<Partner> suppliers = partnerService.getAllSupplier();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("page", "Suppliers List");

        List<Partner> customers = partnerService.getAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("page", "Customer List");

        List<ProducedProduct> producedProducts = producedProductService.getAllProduct();
        model.addAttribute("producedProducts", producedProducts);
        model.addAttribute("page", "Produced Products");
        model.addAttribute("form", new ProducedProductSearchForm());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "main";
    }


}

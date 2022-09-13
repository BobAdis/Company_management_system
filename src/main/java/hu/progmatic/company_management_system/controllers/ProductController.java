package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.Product;
import hu.progmatic.company_management_system.models.ProductCondition;
import hu.progmatic.company_management_system.models.Warehouse;
import hu.progmatic.company_management_system.searchform.ProductSearchForm;
import hu.progmatic.company_management_system.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    /*@GetMapping(value = {"/rawmaterials"})
    public String saveNewSpaceShip(Model model) {
        model.addAttribute("newShip", new SpaceShip());
        model.addAttribute("shipClasses", SpaceShipClass.values());
        return "products";
    }*/
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(value = {"/finishedproducts"})
    public String getFinishedProductsPage(Model model) {
        List<Product> finished = productService.getAllFinishedProducts();
        model.addAttribute("products", finished);
        model.addAttribute("page", "Finished Products");
        model.addAttribute("endpoint", "finishedproducts");
        model.addAttribute("form", new Product());
        return "products";
    }

    @PostMapping("/finishedproducts")
    public String displayFinishedSearchResults(ProductSearchForm form, Model model) {
        List<Product> products = productService.getFinishedByForm(form);
        model.addAttribute("products", products);
        model.addAttribute("page", "Finished Products");
        model.addAttribute("endpoint", "finishedproducts");
        model.addAttribute("form", form);
        return "products";
    }

    @GetMapping(value = {"/rawmaterials"})
    public String getRawMaterialsPage(Model model) {
        List<Product> raws = productService.getAllRawMaterialProducts();
        model.addAttribute("products", raws);
        model.addAttribute("page", "Raw Materials");
        model.addAttribute("endpoint", "rawmaterials");
        model.addAttribute("form", new Product());
        return "products";
    }

    @PostMapping("/rawmaterials")
    public String displayRawSearchResults(ProductSearchForm form, Model model) {
        List<Product> products = productService.getRawByForm(form);
        model.addAttribute("products", products);
        model.addAttribute("page", "Raw Materials");
        model.addAttribute("endpoint", "rawmaterials");
        model.addAttribute("form", form);
        return "products";
    }

    @GetMapping(value = {"/newrawmaterial"})
    public String getNewRawMaterialForm(Model model) {
        Product product = new Product();

        model.addAttribute("product", product);
        model.addAttribute("productCondition", ProductCondition.RAWMATERIAL);
        model.addAttribute("material", "Raw Material");

        return "new_product";
    }

    @GetMapping(value = {"/newfinishedmaterial"})
    public String getNewProductForm(Model model) {
        Product product = new Product();

        model.addAttribute("product", product);
        model.addAttribute("productCondition", ProductCondition.FINISHED);
        model.addAttribute("material", "Finished Material");

        return "new_product";
    }

    @PostMapping(value = {"/newproduct"})
    public String addNewProduct(Product product) {
        productService.saveProduct(product);

        if(product.getProductCondition().equals(ProductCondition.RAWMATERIAL)) {
            return "redirect:/rawmaterials";
        } else {
            return "redirect:/finishedproducts";
        }

    }


}
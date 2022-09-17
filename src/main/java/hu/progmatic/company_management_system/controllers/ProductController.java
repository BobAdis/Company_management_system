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

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(value = {"/products"})
    public String getProductsPage(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        model.addAttribute("page", "Products");
        model.addAttribute("form", new Product());
        return "products";
    }
    @PostMapping("/products")
    public String displayProiductsSearchResults(ProductSearchForm form, Model model) {
        List<Product> products = productService.getByForm(form);
        model.addAttribute("products", products);
        model.addAttribute("page", "Products");
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

    /*@PostMapping(value = {"/newproduct"})
    public String addNewProduct(Product product) {
        productService.saveProduct(product);

        if(product.getProductCondition().equals(ProductCondition.RAWMATERIAL)) {
            return "redirect:/rawmaterials";
        } else {
            return "redirect:/finishedproducts";
        }

    }*/


}
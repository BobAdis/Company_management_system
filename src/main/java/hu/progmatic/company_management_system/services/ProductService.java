package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.Product;
import hu.progmatic.company_management_system.repositories.ProductRepo;

import hu.progmatic.company_management_system.searchform.ProductSearchForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;



    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    public List<Product> getByForm(ProductSearchForm form) {
        List<Product> result = new ArrayList<>();

        for (Product product : getAllProduct()) {
                if (form.getName() != null && !product.getName().contains(form.getName())) {
                    continue;
                }

                if (form.getSerialNumber() != null && product.getSerialNumber().toString().contains(form.getSerialNumber().toString())) {
                    continue;
                }

                result.add(product);
            }
        return result;
    }
}

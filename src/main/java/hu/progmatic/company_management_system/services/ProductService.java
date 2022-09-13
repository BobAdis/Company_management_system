package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.Product;
import hu.progmatic.company_management_system.models.ProductCondition;
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

    public Iterable<Product> getAllProduct() {
        return productRepo.findAll();
    }

    public List<Product> getAllRawMaterialProducts () {
        return productRepo.findByProductConditionEquals(ProductCondition.RAWMATERIAL);
    }

    public List<Product> getAllFinishedProducts () {
        return productRepo.findByProductConditionEquals(ProductCondition.FINISHED);
    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    public List<Product> getRawByForm(ProductSearchForm form) {
        List<Product> result = new ArrayList<>();

        for (Product product : getAllProduct()) {
            if (product.getProductCondition().equals(ProductCondition.RAWMATERIAL)) {
                if (form.getName() != null && !product.getName().contains(form.getName())) {
                    continue;
                }

                if (form.getSerialNumber() != 0 && product.getSerialNumber() != form.getSerialNumber()) {
                    continue;
                }

                result.add(product);
            }
        }
        return result;
    }

    public List<Product> getFinishedByForm(ProductSearchForm form) {
        List<Product> result = new ArrayList<>();

        for (Product product : getAllProduct()) {
            if (form.getProductCondition().equals(ProductCondition.FINISHED)) {
                if (form.getName() != null && !product.getName().contains(form.getName())) {
                    continue;
                }

                if (form.getSerialNumber() != 0 && product.getSerialNumber() != form.getSerialNumber()) {
                    continue;
                }

                result.add(product);
            }
        }
        return result;
    }
}

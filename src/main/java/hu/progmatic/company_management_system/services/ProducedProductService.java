package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.production.ProducedProduct;
import hu.progmatic.company_management_system.repositories.ProducedProductRepo;

import hu.progmatic.company_management_system.searchform.ProducedProductSearchForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProducedProductService {

    private final ProducedProductRepo producedProductRepo;



    public ProducedProductService(ProducedProductRepo producedProductRepo) {
        this.producedProductRepo = producedProductRepo;
    }

    public List<ProducedProduct> getAllProduct() {
        return producedProductRepo.findAll();
    }

    public void saveProduct(ProducedProduct producedProduct) {
        producedProductRepo.save(producedProduct);
    }

    public List<ProducedProduct> getByForm(ProducedProductSearchForm form) {
        List<ProducedProduct> result = new ArrayList<>();

        for (ProducedProduct producedProduct : getAllProduct()) {
            if (form.getName() != null && !producedProduct.getName().contains(form.getName())) {
                continue;
            }
            if (form.getBomList() != null && !producedProduct.getBomList().getName().contains(form.getBomList())) {
                continue;
            }
                result.add(producedProduct);
            }
        return result;
    }

    public ProducedProduct getById(long id) {
        return producedProductRepo.findById(id).orElseThrow();
    }

    public List<ProducedProduct> getWhereBomListIsNull() {
        return producedProductRepo.findAllByBomListNull();
    }
}

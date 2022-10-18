package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.production.EndProduct;
import hu.progmatic.company_management_system.repositories.EndProductRepo;
import hu.progmatic.company_management_system.searchform.EndProductSearchForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EndProductService {

    private final EndProductRepo endProductRepo;

    public EndProductService(EndProductRepo endProductRepo) {
        this.endProductRepo = endProductRepo;
    }

    public List<EndProduct> getAllEndProduct() {return endProductRepo.findAll();}

    public List<EndProduct> getByForm(EndProductSearchForm form) {
        List<EndProduct> result = new ArrayList<>();

        for (EndProduct endProduct : getAllEndProduct()) {
            if (form.getProducedProduct() != null && !endProduct.getProducedProduct().getName().contains(form.getProducedProduct())) {
                continue;
            }
            if (form.getSerialNumber() != null && !endProduct.getSerialNumber().toString().contains(form.getSerialNumber().toString())) {
                continue;
            }
            if (form.getShippingOut() != null && !endProduct.getShippingOut().getLocalDate().equals(form.getShippingOut())) {
                continue;
            }
            result.add(endProduct);
        }
        return result;
    }

    public EndProduct getById(long id) {
        return endProductRepo.findById(id).orElseThrow();
    }

    public List<EndProduct> getEndProductWhereShippingOutIsNull() {
        return endProductRepo.getEndProductByShippingOutNull();
    }

    public void save(EndProduct endProduct) {
        endProductRepo.save(endProduct);
    }
}

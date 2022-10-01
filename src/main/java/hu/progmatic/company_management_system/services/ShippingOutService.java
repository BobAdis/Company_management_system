package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.EndProduct;
import hu.progmatic.company_management_system.models.ShippingOut;
import hu.progmatic.company_management_system.repositories.ShippingOutRepo;
import hu.progmatic.company_management_system.searchform.ShippingOutSearchForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingOutService {

    private final ShippingOutRepo shippingOutRepo;

    public ShippingOutService(ShippingOutRepo shippingOutRepo) {
        this.shippingOutRepo = shippingOutRepo;
    }


    public List<ShippingOut> getAllShippingOut() {
        return shippingOutRepo.findAll();
    }

    public List<ShippingOut> getByForm(ShippingOutSearchForm form) {
        List<ShippingOut> result = new ArrayList<>();

        for (ShippingOut shippingOut : getAllShippingOut()) {
            if (form.getBuyer() != null && !shippingOut.getBuyer().getPartnerName().contains(form.getBuyer())) {
                continue;
            }
            if (form.getEndProductName() != null && !isShippingOutContains(form, shippingOut)) {
                continue;
            }
            if (form.getLocalDate() != null && !shippingOut.getLocalDate().equals(form.getLocalDate())) {
                continue;
            }
            result.add(shippingOut);
        }
        return result;
    }

    public boolean isShippingOutContains(ShippingOutSearchForm form, ShippingOut shippingOut) {
        for (EndProduct endProduct  : shippingOut.getEndProducts()) {
            if (endProduct.getProducedProduct().getName().contains(form.getEndProductName())) {
                return true;
            }
        }
        return false;
    }

    public void saveShippingOut(ShippingOut shippingOut) {
        shippingOutRepo.save(shippingOut);
    }

    public ShippingOut getById(long id) {
        return shippingOutRepo.findById(id).orElseThrow();
    }
}

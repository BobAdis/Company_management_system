package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.Shipping;
import hu.progmatic.company_management_system.repositories.ShippingRepo;
import hu.progmatic.company_management_system.searchform.ShippnigSearchForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingService {

    private final ShippingRepo shippingRepo;

    public ShippingService(ShippingRepo shippingRepo) {
        this.shippingRepo = shippingRepo;
    }

    public List<Shipping> getAllShipping() {
        return shippingRepo.findAll();
    }

    public List<Shipping> getByForm(ShippnigSearchForm form) {
        List<Shipping> result = new ArrayList<>();

        for (Shipping shipping : getAllShipping()) {
            if (form.getSeller() != null && !shipping.getSeller().getPartnerName().contains(form.getSeller())) {
                continue;
            }
            if (form.getRawMaterialName() != null && !isRawMaterialContains(form, shipping)) {
                continue;
            }
            if (form.getLocalDate() != null && !shipping.getLocalDate().equals(form.getLocalDate())) {
                continue;
            }
            result.add(shipping);
        }
        return result;
    }

    public boolean isRawMaterialContains(ShippnigSearchForm form, Shipping shipping) {
        return shipping.getRawMaterials().stream().filter(s ->s.getIngredient().getName().contains(form.getRawMaterialName())).isParallel();
    }
}

package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.RawMaterial;
import hu.progmatic.company_management_system.models.ShippingIn;
import hu.progmatic.company_management_system.repositories.ShippingInRepo;
import hu.progmatic.company_management_system.searchform.ShippingInSearchForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingInService {

    private final ShippingInRepo shippingInRepo;

    public ShippingInService(ShippingInRepo shippingInRepo) {
        this.shippingInRepo = shippingInRepo;
    }

    public List<ShippingIn> getAllShippingIn() {
        return shippingInRepo.findAll();
    }

    public List<ShippingIn> getByForm(ShippingInSearchForm form) {
        List<ShippingIn> result = new ArrayList<>();

        for (ShippingIn shippingIn : getAllShippingIn()) {
            if (form.getSeller() != null && !shippingIn.getSeller().getPartnerName().contains(form.getSeller())) {
                continue;
            }
            if (form.getRawMaterialName() != null && !isRawMaterialContains(form, shippingIn)) {
                continue;
            }
            if (form.getLocalDate() != null && !shippingIn.getLocalDate().equals(form.getLocalDate())) {
                continue;
            }
            result.add(shippingIn);
        }
        return result;
    }

    public boolean isRawMaterialContains(ShippingInSearchForm form, ShippingIn shippingIn) {
        for (RawMaterial rawMaterial : shippingIn.getRawMaterials()) {
            if (rawMaterial.getIngredient().getName().contains(form.getRawMaterialName())) {
                return true;
            }
        }
        return false;
    }

    public void saveShippingIn(ShippingIn shippingIn) {
        shippingInRepo.save(shippingIn);
    }
}

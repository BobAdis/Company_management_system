package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.RawMaterial;
import hu.progmatic.company_management_system.repositories.RawMaterialRepo;
import hu.progmatic.company_management_system.searchform.RawMaterialSearchForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RawMaterialService {

    private final RawMaterialRepo rawMaterialRepo;

    public RawMaterialService(RawMaterialRepo rawMaterialRepo) {
        this.rawMaterialRepo = rawMaterialRepo;
    }

    public List<RawMaterial> getAllRawMaterial() {
        return rawMaterialRepo.findAll();
    }

    public List<RawMaterial> getByForm(RawMaterialSearchForm form) {
        List<RawMaterial> result = new ArrayList<>();

        for (RawMaterial rawMaterial : getAllRawMaterial()) {
            if (form.getIngredient() != null && !rawMaterial.getIngredient().getName().contains(form.getIngredient())) {
                continue;
            }
            if (form.getSARZSNumber() != null && !rawMaterial.getSARZSNumber().toString().contains(form.getSARZSNumber().toString())) {
                continue;
            }
            if (form.getShippingIn() != null && !rawMaterial.getShippingIn().getLocalDate().equals(form.getShippingIn())) {
                continue;
            }
            result.add(rawMaterial);
        }
        return result;
    }

    public RawMaterial getById(long id) {
        return rawMaterialRepo.findById(id).orElseThrow();
    }

    public List<RawMaterial> getWhereShippingInIsNull() {
        return rawMaterialRepo.findAllByShippingInNull();
    }
}

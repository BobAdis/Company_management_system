package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.production.RawMaterial;
import hu.progmatic.company_management_system.models.production.Warehouse;
import hu.progmatic.company_management_system.repositories.RawMaterialRepo;
import hu.progmatic.company_management_system.searchform.RawMaterialSearchForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public RawMaterial transferData(Integer sarzsN, int quantity, Warehouse warehouseout, Warehouse warehousein) {

        RawMaterial transfered = new RawMaterial();

        for (RawMaterial rawMaterial1 : getAllRawMaterial()) {
            if (rawMaterial1.getSARZSNumber().equals(sarzsN) && rawMaterial1.getWarehouse().equals(warehouseout)){
                rawMaterial1.setQuantity(rawMaterial1.getQuantity() - quantity);
                transfered.setQuantity(quantity);
                transfered.setSARZSNumber(sarzsN);
                transfered.setIngredient(rawMaterial1.getIngredient());
                transfered.setShippingIn(rawMaterial1.getShippingIn());
                transfered.setUnitPrice(rawMaterial1.getUnitPrice());
                break;
            }
        }
        for (RawMaterial rawMaterial2 : getAllRawMaterial()) {
            if (transfered.getSARZSNumber().equals(rawMaterial2.getSARZSNumber()) && rawMaterial2.getWarehouse().equals(warehousein)) {
                rawMaterial2.setQuantity(rawMaterial2.getQuantity() + quantity);
                return transfered;
            }
        }
        transfered.setWarehouse(warehousein);
        rawMaterialRepo.save(transfered);
        return transfered;
    }

    public List<RawMaterial> getAllRawMaterialbySARZSbyWarehouse(Warehouse warehouse) {
        List<RawMaterial> result = new ArrayList<>();
        for (RawMaterial rawMaterial : rawMaterialRepo.getRawMaterialByWarehouse(warehouse)) {
            if (result.isEmpty()) {
                result.add(rawMaterial);
                continue;
            }
            for(RawMaterial rawMaterial1 : result) {
                if (rawMaterial.getSARZSNumber().equals(rawMaterial1.getSARZSNumber())) {
                    rawMaterial1.setQuantity(rawMaterial1.getQuantity() + rawMaterial.getQuantity());
                    break;
                }
                result.add(rawMaterial);
                break;
            }
        }
        return result;
    }

    public RawMaterial getById(long id) {
        return rawMaterialRepo.findById(id).orElseThrow();
    }

    public List<RawMaterial> getWhereShippingInIsNull() {
        return rawMaterialRepo.findAllByShippingInNull();
    }

    public boolean subtractRawMaterial(Long rawMaterialId, int quantity) {
        RawMaterial rawMaterial = getById(rawMaterialId);
        int currentQuantity = rawMaterial.getQuantity();

        if(currentQuantity > 0 && currentQuantity >= quantity) {
            rawMaterial.setQuantity(currentQuantity-quantity);

            rawMaterialRepo.save(rawMaterial);
            return true;
        } else {
            return false;
        }
    }
}

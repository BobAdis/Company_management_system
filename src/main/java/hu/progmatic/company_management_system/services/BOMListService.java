package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.BOMList;
import hu.progmatic.company_management_system.models.Ingredient;
import hu.progmatic.company_management_system.repositories.BOMListRepo;
import hu.progmatic.company_management_system.searchform.BOMListSearchForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BOMListService {

    private final BOMListRepo bomListRepo;

    public BOMListService(BOMListRepo bomListRepo) {
        this.bomListRepo = bomListRepo;
    }


    public List<BOMList> getAllBOMList() {
        return bomListRepo.findAll();
    }

    public List<BOMList> getByForm(BOMListSearchForm form) {
        List<BOMList> result = new ArrayList<>();

        for (BOMList bomList : getAllBOMList()) {
            if (form.getName() != null && !bomList.getName().contains(form.getName())) {
                continue;
            }
            if (form.getProducedProduct() != null && !bomList.getProducedProduct().getName().contains(form.getProducedProduct())) {
                continue;
            }
            if (form.getIngredient() != null && !isIngredientContains(form, bomList)) {
                continue;
            }
            result.add(bomList);
        }
        return result;
    }

    public boolean isContains () {
        return true;
    }

    public boolean isIngredientContains(BOMListSearchForm form, BOMList bomList) {
        for (Ingredient ingredient : bomList.getIngredients()) {
            if (ingredient.getName().contains(form.getIngredient())) {
                return true;
            }
        }
        return false;
    }

    public void saveBomList(BOMList bomList) {
        bomListRepo.save(bomList);
    }

    public BOMList getBOMListById(long id) {
        return bomListRepo.findById(id).orElseThrow();
    }

   /* public List<Product> ria(ProductCondition condition, ProductSearchForm form) {
        return ((List<Product>) getAllProduct()).stream()
                .filter(product ->
                        product.getProductCondition() == condition &&
                                (isNameOk(form, product) ||
                                        isNumberOk(form, product)))
                .toList();
    }

    private boolean isNameOk(ProductSearchForm form, Product product) {
        return form.getName() != null && product.getName().contains(form.getName());
    }

    private boolean isNumberOk(ProductSearchForm form, Product product) {
        return form.getSerialNumber() != 0 && product.getSerialNumber() == form.getSerialNumber();
    }*/

}

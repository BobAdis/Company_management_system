package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.Ingredient;
import hu.progmatic.company_management_system.repositories.IngredientRepo;
import hu.progmatic.company_management_system.searchform.IngredientSearchForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepo ingredientRepo;

    public IngredientService(IngredientRepo ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    public List<Ingredient> getAllIngredient() {
        return ingredientRepo.findAll();
    }

    public List<Ingredient> getByForm(IngredientSearchForm form) {
        List<Ingredient> result = new ArrayList<>();

        for (Ingredient ingredient : getAllIngredient()) {
            if (form.getName() != null && !ingredient.getName().contains(form.getName())) {
                continue;
            }
            if (form.getBomList() != null && !ingredient.getBomList().getName().contains(form.getBomList())) {
                continue;
            }
            result.add(ingredient);
        }
        return result;
    }

    public void saveIngredient(Ingredient ingredient) {
        ingredientRepo.save(ingredient);
    }

    public Ingredient getById(long id) {
        return ingredientRepo.findById(id).orElseThrow();
    }
    public List<Ingredient> getWhereBomListIsNull() {
        return ingredientRepo.findAllByBomListNull();
    }
}

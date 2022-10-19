package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.production.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepo extends CrudRepository<Ingredient, Long> {

    List<Ingredient> findAll();
    List<Ingredient> findAllByBomListNull();
}

package hu.progmatic.company_management_system;

import hu.progmatic.company_management_system.models.BOMList;
import hu.progmatic.company_management_system.models.Ingredient;
import hu.progmatic.company_management_system.repositories.IngredientRepo;
import hu.progmatic.company_management_system.searchform.IngredientSearchForm;
import hu.progmatic.company_management_system.services.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestIngredientService {

    @Mock
    private IngredientRepo ingredientRepo;

    @InjectMocks
    private IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetByForm() {
        String goodName = "A";
        String badName = "B";

        BOMList goodBomList = new BOMList();
        goodBomList.setId(1L);
        goodBomList.setName("x");

        BOMList badBomList = new BOMList();
        badBomList.setId(2L);
        badBomList.setName("z");

        Ingredient goodIngredient = new Ingredient();
        goodIngredient.setId(1L);
        goodIngredient.setName(goodName);
        goodIngredient.setBomList(goodBomList);

        Ingredient badIngredient = new Ingredient();
        badIngredient.setId(2L);
        badIngredient.setName(badName);
        badIngredient.setBomList(goodBomList);

        Ingredient badIngredient2 = new Ingredient();
        badIngredient2.setId(3L);
        badIngredient2.setName(goodName);
        badIngredient2.setBomList(badBomList);

        when(ingredientRepo.findAll()).thenReturn(List.of(goodIngredient, badIngredient2, badIngredient));

        IngredientSearchForm form = new IngredientSearchForm();
        form.setBomList(goodBomList.getName());
        form.setName(goodName);

        List<Ingredient> results = ingredientService.getByForm(form);

        assertEquals(1, results.size());
    }
}

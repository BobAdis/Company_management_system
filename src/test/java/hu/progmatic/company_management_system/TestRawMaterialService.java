package hu.progmatic.company_management_system;

import hu.progmatic.company_management_system.models.production.Ingredient;
import hu.progmatic.company_management_system.models.production.RawMaterial;
import hu.progmatic.company_management_system.models.sales.ShippingIn;
import hu.progmatic.company_management_system.repositories.RawMaterialRepo;
import hu.progmatic.company_management_system.searchform.RawMaterialSearchForm;
import hu.progmatic.company_management_system.services.RawMaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestRawMaterialService {

    @Mock
    private RawMaterialRepo rawMaterialRepo;

    @InjectMocks
    private RawMaterialService rawMaterialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetByForm() {
        int goodSARZSNumber = 1;
        int badSARZSNumber = 2;

        Ingredient goodIngredient = new Ingredient();
        goodIngredient.setId(1L);
        goodIngredient.setName("A");

        Ingredient badIngredient = new Ingredient();
        badIngredient.setId(2L);
        badIngredient.setName("B");

        ShippingIn goodShippingIn = new ShippingIn();
        goodShippingIn.setId(1L);
        goodShippingIn.setLocalDate(LocalDate.of(1992, 10, 2));

        ShippingIn badShippingIn = new ShippingIn();
        badShippingIn.setId(2L);
        badShippingIn.setLocalDate(LocalDate.of(1993, 10, 2));

        RawMaterial goodRawMaterial = new RawMaterial();
        goodRawMaterial.setId(1L);
        goodRawMaterial.setIngredient(goodIngredient);
        goodRawMaterial.setSARZSNumber(goodSARZSNumber);
        goodRawMaterial.setShippingIn(goodShippingIn);

        RawMaterial badMaterial1 = new RawMaterial();
        badMaterial1.setId(2L);
        badMaterial1.setIngredient(badIngredient);
        badMaterial1.setSARZSNumber(goodSARZSNumber);
        badMaterial1.setShippingIn(goodShippingIn);

        RawMaterial badMaterial2 = new RawMaterial();
        badMaterial2.setId(3L);
        badMaterial2.setIngredient(goodIngredient);
        badMaterial2.setSARZSNumber(badSARZSNumber);
        badMaterial2.setShippingIn(goodShippingIn);

        RawMaterial badMaterial3 = new RawMaterial();
        badMaterial3.setId(4L);
        badMaterial3.setIngredient(goodIngredient);
        badMaterial3.setSARZSNumber(goodSARZSNumber);
        badMaterial3.setShippingIn(badShippingIn);

        List<RawMaterial> materials = List.of(goodRawMaterial, badMaterial2, badMaterial3, badMaterial1);

        when(rawMaterialRepo.findAll()).thenReturn(materials);

        RawMaterialSearchForm form = new RawMaterialSearchForm();
        form.setIngredient(goodIngredient.getName());
        form.setSARZSNumber(goodSARZSNumber);
        form.setShippingIn(goodShippingIn.getLocalDate());

        List<RawMaterial> results = rawMaterialService.getByForm(form);

        assertEquals(1, results.size());
    }
}

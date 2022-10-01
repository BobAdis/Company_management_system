package hu.progmatic.company_management_system;


import hu.progmatic.company_management_system.models.*;
import hu.progmatic.company_management_system.repositories.ShippingInRepo;
import hu.progmatic.company_management_system.searchform.ShippingInSearchForm;
import hu.progmatic.company_management_system.services.ShippingInService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestShippingInService {

    @Mock
    private ShippingInRepo shippingInRepo;

    @InjectMocks
    private ShippingInService shippingInService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetByForm() {
        Partner goodPartner = new Partner();
        goodPartner.setId(1L);
        goodPartner.setPartnerName("a");

        Partner badPartner = new Partner();
        badPartner.setId(2L);
        badPartner.setPartnerName("b");

        Ingredient goodIngredient = new Ingredient();
        goodIngredient.setId(1L);
        goodIngredient.setName("A");

        Ingredient badIngredient = new Ingredient();
        badIngredient.setId(2L);
        badIngredient.setName("B");

        RawMaterial goodRawMaterial = new RawMaterial();
        goodRawMaterial.setId(1L);
        goodRawMaterial.setIngredient(goodIngredient);

        RawMaterial badRawMaterial = new RawMaterial();
        badRawMaterial.setId(2L);
        badRawMaterial.setIngredient(badIngredient);

        ShippingIn shippingIn1 = new ShippingIn();
        shippingIn1.setId(1L);
        shippingIn1.setSeller(goodPartner);
        shippingIn1.setLocalDate(LocalDate.of(1992, 10, 2));
        shippingIn1.setRawMaterials(List.of(goodRawMaterial));

        ShippingIn shippingIn2 = new ShippingIn();
        shippingIn2.setId(2L);
        shippingIn2.setSeller(badPartner);
        shippingIn2.setLocalDate(LocalDate.of(1992, 10, 2));
        shippingIn2.setRawMaterials(List.of(goodRawMaterial));

        ShippingIn shippingIn3 = new ShippingIn();
        shippingIn3.setId(3L);
        shippingIn3.setSeller(goodPartner);
        shippingIn3.setLocalDate(LocalDate.of(1993, 10, 2));
        shippingIn3.setRawMaterials(List.of(goodRawMaterial));

        ShippingIn shippingIn4 = new ShippingIn();
        shippingIn4.setId(4L);
        shippingIn4.setSeller(goodPartner);
        shippingIn4.setLocalDate(LocalDate.of(1992, 10, 2));
        shippingIn4.setRawMaterials(List.of(badRawMaterial));

        when(shippingInRepo.findAll()).thenReturn(List.of(shippingIn1, shippingIn2, shippingIn3, shippingIn4));

        ShippingInSearchForm shippingInSearchForm = new ShippingInSearchForm();
        shippingInSearchForm.setLocalDate(LocalDate.of(1992,10,2));
        shippingInSearchForm.setSeller(goodPartner.getPartnerName());
        shippingInSearchForm.setRawMaterialName(goodRawMaterial.getIngredient().getName());

        List<ShippingIn> results = shippingInService.getByForm(shippingInSearchForm);

        assertEquals(1, results.size());
    }
}

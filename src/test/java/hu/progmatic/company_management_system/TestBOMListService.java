package hu.progmatic.company_management_system;

import hu.progmatic.company_management_system.models.*;
import hu.progmatic.company_management_system.repositories.BOMListRepo;
import hu.progmatic.company_management_system.searchform.BOMListSearchForm;
import hu.progmatic.company_management_system.searchform.EndProductSearchForm;
import hu.progmatic.company_management_system.services.BOMListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestBOMListService {

    @Mock
    private BOMListRepo bomListRepo;

    @InjectMocks
    private BOMListService bomListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetByForm() {
        ProducedProduct goodProduct = new ProducedProduct();
        goodProduct.setId(1L);
        goodProduct.setName("A");

        ProducedProduct badProduct = new ProducedProduct();
        badProduct.setId(2L);
        badProduct.setName("B");

        Ingredient goodIngredient = new Ingredient();
        goodIngredient.setId(1L);
        goodIngredient.setName("c");

        Ingredient badIngredient = new Ingredient();
        badIngredient.setId(2L);
        badIngredient.setName("d");

        BOMList goodBomList = new BOMList();
        goodBomList.setId(1L);
        goodBomList.setName("x");
        goodBomList.setIngredients(List.of(goodIngredient));
        goodBomList.setProducedProduct(goodProduct);

        BOMList badBomList = new BOMList();
        badBomList.setId(2L);
        badBomList.setName("z");
        badBomList.setIngredients(List.of(goodIngredient));
        badBomList.setProducedProduct(goodProduct);

        BOMList badBomList2 = new BOMList();
        badBomList2.setId(3L);
        badBomList2.setName("x");
        badBomList2.setIngredients(List.of(badIngredient));
        badBomList2.setProducedProduct(goodProduct);

        BOMList badBomList3 = new BOMList();
        badBomList3.setId(4L);
        badBomList3.setName("x");
        badBomList3.setIngredients(List.of(goodIngredient));
        badBomList3.setProducedProduct(badProduct);

        when(bomListRepo.findAll()).thenReturn(List.of(goodBomList, badBomList2, badBomList3, badBomList));

        BOMListSearchForm form = new BOMListSearchForm();
        form.setIngredient(goodIngredient.getName());
        form.setName(goodBomList.getName());
        form.setProducedProduct(goodProduct.getName());

        List<BOMList> result = bomListService.getByForm(form);

        assertEquals(1, result.size());


    }
}

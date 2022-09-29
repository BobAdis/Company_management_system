package hu.progmatic.company_management_system;

import hu.progmatic.company_management_system.models.BOMList;
import hu.progmatic.company_management_system.models.ProducedProduct;
import hu.progmatic.company_management_system.repositories.ProducedProductRepo;
import hu.progmatic.company_management_system.searchform.ProducedProductSearchForm;
import hu.progmatic.company_management_system.services.ProducedProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestProducedProductService {

    @Mock
    private ProducedProductRepo producedProductRepo;

    @InjectMocks
    private ProducedProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetByForm() {
        BOMList goodBomList = new BOMList();
        goodBomList.setId(1L);
        goodBomList.setName("x");

        BOMList badBomList = new BOMList();
        badBomList.setId(2L);
        badBomList.setName("z");

        ProducedProduct goodProduct = new ProducedProduct();
        goodProduct.setId(1L);
        goodProduct.setName("A");
        goodProduct.setBomList(goodBomList);

        ProducedProduct badProduct = new ProducedProduct();
        badProduct.setId(1L);
        badProduct.setName("B");
        badProduct.setBomList(goodBomList);

        ProducedProduct badProduct2 = new ProducedProduct();
        badProduct2.setId(1L);
        badProduct2.setName("A");
        badProduct2.setBomList(badBomList);

        when(producedProductRepo.findAll()).thenReturn(List.of(goodProduct, badProduct2, badProduct));

        ProducedProductSearchForm form = new ProducedProductSearchForm();
        form.setName("A");
        form.setBomList(goodBomList.getName());

        List<ProducedProduct> producedProducts = productService.getByForm(form);

        assertEquals(1, producedProducts.size());
    }
}

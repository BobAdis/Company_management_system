package hu.progmatic.company_management_system;

import hu.progmatic.company_management_system.models.production.EndProduct;
import hu.progmatic.company_management_system.models.production.ProducedProduct;
import hu.progmatic.company_management_system.models.sales.ShippingOut;
import hu.progmatic.company_management_system.repositories.EndProductRepo;
import hu.progmatic.company_management_system.searchform.EndProductSearchForm;
import hu.progmatic.company_management_system.services.EndProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestEndProductService {

    @Mock
    private EndProductRepo endProductRepo;

    @InjectMocks
    private EndProductService endProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetByForm() {

        Integer goodSerialNumber = 1;
        Integer badSerialNumber = 2;

        ProducedProduct goodProduct = new ProducedProduct();
        goodProduct.setId(1L);
        goodProduct.setName("A");

        ProducedProduct badProduct = new ProducedProduct();
        badProduct.setId(1L);
        badProduct.setName("B");

        ShippingOut goodShippingOut = new ShippingOut();
        goodShippingOut.setId(1L);
        goodShippingOut.setLocalDate(LocalDate.of(1992, 10, 2));

        ShippingOut badShippingOut = new ShippingOut();
        badShippingOut.setId(2L);
        badShippingOut.setLocalDate(LocalDate.of(1993, 10, 2));

        EndProduct goodEndProduct = new EndProduct();
        goodEndProduct.setId(1L);
        goodEndProduct.setSerialNumber(goodSerialNumber);
        goodEndProduct.setShippingOut(goodShippingOut);
        goodEndProduct.setProducedProduct(goodProduct);

        EndProduct badEndProduct1 = new EndProduct();
        badEndProduct1.setId(2L);
        badEndProduct1.setSerialNumber(badSerialNumber);
        badEndProduct1.setShippingOut(goodShippingOut);
        badEndProduct1.setProducedProduct(goodProduct);

        EndProduct badEndProduct2 = new EndProduct();
        badEndProduct2.setId(3L);
        badEndProduct2.setSerialNumber(goodSerialNumber);
        badEndProduct2.setShippingOut(badShippingOut);
        badEndProduct2.setProducedProduct(goodProduct);

        EndProduct badEndProduct3 = new EndProduct();
        badEndProduct3.setId(4L);
        badEndProduct3.setSerialNumber(goodSerialNumber);
        badEndProduct3.setShippingOut(goodShippingOut);
        badEndProduct3.setProducedProduct(badProduct);

        when(endProductRepo.findAll()).thenReturn(List.of(goodEndProduct, badEndProduct2, badEndProduct3, badEndProduct1));

        EndProductSearchForm form = new EndProductSearchForm();
        form.setProducedProduct(goodProduct.getName());
        form.setSerialNumber(goodSerialNumber);
        form.setShippingOut(goodShippingOut.getLocalDate());

        List<EndProduct> endProducts = endProductService.getByForm(form);

        assertEquals(1, endProducts.size());
    }
}

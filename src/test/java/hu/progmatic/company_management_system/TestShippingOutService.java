package hu.progmatic.company_management_system;


import hu.progmatic.company_management_system.models.production.EndProduct;
import hu.progmatic.company_management_system.models.sales.Partner;
import hu.progmatic.company_management_system.models.production.ProducedProduct;
import hu.progmatic.company_management_system.models.sales.ShippingOut;
import hu.progmatic.company_management_system.repositories.ShippingOutRepo;
import hu.progmatic.company_management_system.searchform.ShippingOutSearchForm;
import hu.progmatic.company_management_system.services.ShippingOutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestShippingOutService {

    @Mock
    private ShippingOutRepo shippingOutRepo;

    @InjectMocks
    private ShippingOutService shippingOutService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetByForm() {
        ShippingOutSearchForm shippingOutSearchForm = new ShippingOutSearchForm();
        shippingOutSearchForm.setBuyer("vasarlo");
        shippingOutSearchForm.setEndProductName("termek");
        shippingOutSearchForm.setLocalDate(LocalDate.of(1992, 10, 2));

        Partner partner1 = new Partner();
        partner1.setId(1L);
        partner1.setPartnerName("vasarlo");

        Partner partner2 = new Partner();
        partner2.setId(2L);
        partner2.setPartnerName("vasarlo2");

        ProducedProduct producedProduct = new ProducedProduct();
        producedProduct.setId(1L);
        producedProduct.setName("termek");

        ProducedProduct producedProduct2 = new ProducedProduct();
        producedProduct2.setId(2L);
        producedProduct2.setName("termek2");

        EndProduct endProduct = new EndProduct();
        endProduct.setId(1L);
        endProduct.setProducedProduct(producedProduct);

        EndProduct endProduct2 = new EndProduct();
        endProduct2.setId(2L);
        endProduct2.setProducedProduct(producedProduct2);

        ShippingOut shippingOut1 = new ShippingOut();
        shippingOut1.setId(1L);
        shippingOut1.setBuyer(partner1);
        shippingOut1.setLocalDate(LocalDate.of(1992, 10, 2));
        shippingOut1.setEndProducts(List.of(endProduct));

        ShippingOut shippingOut2 = new ShippingOut();
        shippingOut2.setId(1L);
        shippingOut2.setBuyer(partner2);
        shippingOut2.setLocalDate(LocalDate.of(1992, 10, 2));
        shippingOut2.setEndProducts(List.of(endProduct));

        ShippingOut shippingOut3 = new ShippingOut();
        shippingOut3.setId(1L);
        shippingOut3.setBuyer(partner1);
        shippingOut3.setLocalDate(LocalDate.of(1993, 10, 2));
        shippingOut3.setEndProducts(List.of(endProduct));

        ShippingOut shippingOut4 = new ShippingOut();
        shippingOut4.setId(1L);
        shippingOut4.setBuyer(partner1);
        shippingOut4.setLocalDate(LocalDate.of(1992, 10, 2));
        shippingOut4.setEndProducts(List.of(endProduct2));

        List<ShippingOut> shippingOutList = List.of(
                shippingOut1, shippingOut2, shippingOut3, shippingOut4
        );

        when(shippingOutRepo.findAll()).thenReturn(shippingOutList);

        List<ShippingOut> results = shippingOutService.getByForm(shippingOutSearchForm);

        assertEquals(3, results.size());
    }
}

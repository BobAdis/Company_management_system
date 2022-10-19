package hu.progmatic.company_management_system;

import hu.progmatic.company_management_system.models.sales.Partner;
import hu.progmatic.company_management_system.models.sales.PartnerType;
import hu.progmatic.company_management_system.repositories.PartnerRepo;
import hu.progmatic.company_management_system.searchform.PartnerSearchForm;
import hu.progmatic.company_management_system.services.PartnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class TestPartnerService {

    @Mock
    private PartnerRepo partnerRepo;

    @InjectMocks
    private PartnerService partnerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCustomers() {
        Partner partner1 = new Partner();
        partner1.setId(1L);
        partner1.setPartnerName("A");
        partner1.setPartnerType(PartnerType.CUSTOMER);
        partner1.setAddress("B");
        partner1.setEmail("D@D");
        partner1.setContactName("C");
        partner1.setPhoneNumber("E");

        Partner partner2 = new Partner();
        partner2.setId(2L);
        partner2.setPartnerName("F");
        partner2.setPartnerType(PartnerType.CUSTOMER);
        partner2.setAddress("G");
        partner2.setEmail("H@H");
        partner2.setContactName("I");
        partner2.setPhoneNumber("J");

        List<Partner> partners = new ArrayList<>();
        partners.add(partner1);
        partners.add(partner2);

        when(partnerRepo.findByPartnerTypeEquals(eq(PartnerType.CUSTOMER)))
                .thenReturn(partners);

        List<Partner> result = partnerService.getAllCustomers();

        assertEquals(2, result.size());
        assertEquals(result.get(0).getPartnerName(), "A");
        assertEquals(result.get(0).getId(), 1L);
        assertEquals(result.get(0).getAddress(), "B");
        assertEquals(result.get(0).getPartnerType(), PartnerType.CUSTOMER);
        assertEquals(result.get(0).getContactName(), "C");
        assertEquals(result.get(0).getEmail(), "D@D");
        assertEquals(result.get(0).getPhoneNumber(), "E");

        assertEquals(result.get(1).getPartnerName(), "F");
        assertEquals(result.get(1).getId(), 2L);
        assertEquals(result.get(1).getAddress(), "G");
        assertEquals(result.get(1).getPartnerType(), PartnerType.CUSTOMER);
        assertEquals(result.get(1).getContactName(), "I");
        assertEquals(result.get(1).getEmail(), "H@H");
        assertEquals(result.get(1).getPhoneNumber(), "J");
    }

    @Test
    public void testGetAllSupplier() {
        Partner partner1 = new Partner();
        partner1.setId(1L);
        partner1.setPartnerName("A");
        partner1.setPartnerType(PartnerType.SUPPLIER);
        partner1.setAddress("B");
        partner1.setEmail("D@D");
        partner1.setContactName("C");
        partner1.setPhoneNumber("E");

        Partner partner2 = new Partner();
        partner2.setId(2L);
        partner2.setPartnerName("F");
        partner2.setPartnerType(PartnerType.SUPPLIER);
        partner2.setAddress("G");
        partner2.setEmail("H@H");
        partner2.setContactName("I");
        partner2.setPhoneNumber("J");

        List<Partner> partners = new ArrayList<>();
        partners.add(partner1);
        partners.add(partner2);

        when(partnerRepo.findByPartnerTypeEquals(eq(PartnerType.SUPPLIER)))
                .thenReturn(partners);

        List<Partner> result = partnerService.getAllSupplier();

        assertEquals(2, result.size());
        assertEquals(result.get(0).getPartnerName(), "A");
        assertEquals(result.get(0).getId(), 1L);
        assertEquals(result.get(0).getAddress(), "B");
        assertEquals(result.get(0).getPartnerType(), PartnerType.SUPPLIER);
        assertEquals(result.get(0).getContactName(), "C");
        assertEquals(result.get(0).getEmail(), "D@D");
        assertEquals(result.get(0).getPhoneNumber(), "E");

        assertEquals(result.get(1).getPartnerName(), "F");
        assertEquals(result.get(1).getId(), 2L);
        assertEquals(result.get(1).getAddress(), "G");
        assertEquals(result.get(1).getPartnerType(), PartnerType.SUPPLIER);
        assertEquals(result.get(1).getContactName(), "I");
        assertEquals(result.get(1).getEmail(), "H@H");
        assertEquals(result.get(1).getPhoneNumber(), "J");
    }

    @Test
    public void testGetCustomerById() {
        Partner partner1 = new Partner();
        partner1.setId(1L);
        partner1.setPartnerName("A");
        partner1.setPartnerType(PartnerType.CUSTOMER);
        partner1.setAddress("B");
        partner1.setEmail("D@D");
        partner1.setContactName("C");
        partner1.setPhoneNumber("E");

        when(partnerRepo.findByPartnerTypeEqualsAndIdEquals(eq(PartnerType.CUSTOMER), eq(1L)))
                .thenReturn(partner1);

        Partner result = partnerService.getCustomerById(1L);

        assertEquals(result.getPartnerName(), "A");
        assertEquals(result.getId(), 1L);
        assertEquals(result.getAddress(), "B");
        assertEquals(result.getPartnerType(), PartnerType.CUSTOMER);
        assertEquals(result.getContactName(), "C");
        assertEquals(result.getEmail(), "D@D");
        assertEquals(result.getPhoneNumber(), "E");
    }

    @Test
    public void testGetSupplierById() {
        Partner partner1 = new Partner();
        partner1.setId(1L);
        partner1.setPartnerName("A");
        partner1.setPartnerType(PartnerType.SUPPLIER);
        partner1.setAddress("B");
        partner1.setEmail("D@D");
        partner1.setContactName("C");
        partner1.setPhoneNumber("E");

        when(partnerRepo.findByPartnerTypeEqualsAndIdEquals(eq(PartnerType.SUPPLIER), eq(1L)))
                .thenReturn(partner1);

        Partner result = partnerService.getSupplierById(1L);

        assertEquals(result.getPartnerName(), "A");
        assertEquals(result.getId(), 1L);
        assertEquals(result.getAddress(), "B");
        assertEquals(result.getPartnerType(), PartnerType.SUPPLIER);
        assertEquals(result.getContactName(), "C");
        assertEquals(result.getEmail(), "D@D");
        assertEquals(result.getPhoneNumber(), "E");
    }

    @Test
    public void testGetByForm_Customer() {
        PartnerSearchForm form = new PartnerSearchForm();
        form.setName("A");

        Partner partner1 = new Partner();
        partner1.setId(1L);
        partner1.setPartnerName("A");
        partner1.setPartnerType(PartnerType.CUSTOMER);
        partner1.setAddress("B");
        partner1.setEmail("D@D");
        partner1.setContactName("C");
        partner1.setPhoneNumber("E");

        Partner partner2 = new Partner();
        partner2.setId(2L);
        partner2.setPartnerName("B");
        partner2.setPartnerType(PartnerType.CUSTOMER);
        partner2.setAddress("B");
        partner2.setEmail("D@D");
        partner2.setContactName("C");
        partner2.setPhoneNumber("E");

        List<Partner> partners = new ArrayList<>();
        partners.add(partner1);
        partners.add(partner2);

        when(partnerRepo.findByPartnerTypeEquals(eq(PartnerType.CUSTOMER)))
                .thenReturn(partners);

        List<Partner> result = partnerService.getByForm(form, PartnerType.CUSTOMER);

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getPartnerName(), "A");
        assertEquals(result.get(0).getId(), 1L);
        assertEquals(result.get(0).getAddress(), "B");
        assertEquals(result.get(0).getPartnerType(), PartnerType.CUSTOMER);
        assertEquals(result.get(0).getContactName(), "C");
        assertEquals(result.get(0).getEmail(), "D@D");
        assertEquals(result.get(0).getPhoneNumber(), "E");
    }

    @Test
    public void testGetByForm_Supplier() {
        PartnerSearchForm form = new PartnerSearchForm();
        form.setName("A");

        Partner partner1 = new Partner();
        partner1.setId(1L);
        partner1.setPartnerName("A");
        partner1.setPartnerType(PartnerType.SUPPLIER);
        partner1.setAddress("B");
        partner1.setEmail("D@D");
        partner1.setContactName("C");
        partner1.setPhoneNumber("E");

        Partner partner2 = new Partner();
        partner2.setId(2L);
        partner2.setPartnerName("B");
        partner2.setPartnerType(PartnerType.SUPPLIER);
        partner2.setAddress("B");
        partner2.setEmail("D@D");
        partner2.setContactName("C");
        partner2.setPhoneNumber("E");

        List<Partner> partners = new ArrayList<>();
        partners.add(partner1);
        partners.add(partner2);

        when(partnerRepo.findByPartnerTypeEquals(eq(PartnerType.SUPPLIER)))
                .thenReturn(partners);

        List<Partner> result = partnerService.getByForm(form, PartnerType.SUPPLIER);

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getPartnerName(), "A");
        assertEquals(result.get(0).getId(), 1L);
        assertEquals(result.get(0).getAddress(), "B");
        assertEquals(result.get(0).getPartnerType(), PartnerType.SUPPLIER);
        assertEquals(result.get(0).getContactName(), "C");
        assertEquals(result.get(0).getEmail(), "D@D");
        assertEquals(result.get(0).getPhoneNumber(), "E");
    }
}

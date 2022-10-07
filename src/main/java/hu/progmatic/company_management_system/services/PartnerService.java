package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.Partner;
import hu.progmatic.company_management_system.models.PartnerType;
import hu.progmatic.company_management_system.repositories.PartnerRepo;
import hu.progmatic.company_management_system.searchform.PartnerSearchForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerService {

    private final PartnerRepo partnerRepo;

    public PartnerService(PartnerRepo customerRepo) {
        this.partnerRepo = customerRepo;
    }

    public List<Partner> getAllCustomers () {
        return partnerRepo.findByPartnerTypeEquals(PartnerType.CUSTOMER);
    }

    public List<Partner> getAllSupplier () {
        return partnerRepo.findByPartnerTypeEquals(PartnerType.SUPPLIER);
    }

    public Partner getCustomerById(long id) {
        return partnerRepo.findByPartnerTypeEqualsAndIdEquals(PartnerType.CUSTOMER, id);
    }

    public Partner getSupplierById(long id) {
        return partnerRepo.findByPartnerTypeEqualsAndIdEquals(PartnerType.SUPPLIER, id);
    }

    public List<Partner> getByForm(PartnerSearchForm form, PartnerType partnerType) {
        List<Partner> result = new ArrayList<>();
        List<Partner> partnersList;
        if (partnerType.equals(PartnerType.SUPPLIER)) {
            partnersList = getAllSupplier();
        } else {
            partnersList = getAllCustomers();
        }
        for (Partner partner : partnersList) {
            if (form.getName() != null && !partner.getPartnerName().contains(form.getName())) {
                continue;
            }
            result.add(partner);
        }
        return result;
    }
}

package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.Partner;
import hu.progmatic.company_management_system.models.PartnerType;
import hu.progmatic.company_management_system.repositories.PartnerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {

    private final PartnerRepo partnerRepo;

    public PartnerService(PartnerRepo customerRepo) {
        this.partnerRepo = customerRepo;
    }

    public List<Partner> getAllCustomers () {
        return partnerRepo.findByPartnerTypeEquals(PartnerType.COSTUMER);
    }

    public List<Partner> getAllSupplier () {
        return partnerRepo.findByPartnerTypeEquals(PartnerType.SUPPLIER);
    }

    public Partner getCustomerById(long id) {
        return partnerRepo.findByPartnerTypeEqualsAndIdEquals(PartnerType.COSTUMER, id);
    }

    public Partner getSupplierById(long id) {
        return partnerRepo.findByPartnerTypeEqualsAndIdEquals(PartnerType.SUPPLIER, id);
    }
}

package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.Partner;
import hu.progmatic.company_management_system.models.PartnerType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartnerRepo extends CrudRepository<Partner, Long> {

    List<Partner> findByPartnerTypeEquals(PartnerType partnerType);
}

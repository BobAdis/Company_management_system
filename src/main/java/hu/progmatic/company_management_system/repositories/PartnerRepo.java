package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.sales.Partner;
import hu.progmatic.company_management_system.models.sales.PartnerType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartnerRepo extends CrudRepository<Partner, Long> {

    List<Partner> findAll();

    List<Partner> findByPartnerTypeEquals(PartnerType partnerType);

    Partner findByPartnerTypeEqualsAndIdEquals(PartnerType partnerType, long id);
}

package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.production.EndProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EndProductRepo extends CrudRepository<EndProduct, Long> {

    List<EndProduct> findAll();

    List<EndProduct> getEndProductByShippingOutNull();
}

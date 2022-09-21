package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.ShippingOut;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShippingOutRepo extends CrudRepository<ShippingOut, Long> {

    List<ShippingOut> findAll();

}

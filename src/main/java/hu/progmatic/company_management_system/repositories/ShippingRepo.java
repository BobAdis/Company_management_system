package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.Shipping;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShippingRepo extends CrudRepository<Shipping, Long> {

    List<Shipping> findAll();

}

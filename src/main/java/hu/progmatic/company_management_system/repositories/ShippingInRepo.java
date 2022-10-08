package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.sales.ShippingIn;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShippingInRepo extends CrudRepository<ShippingIn, Long> {

    List<ShippingIn> findAll();

}

package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.ProducedProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProducedProductRepo extends CrudRepository<ProducedProduct, Long> {

    List<ProducedProduct> findAll();
}

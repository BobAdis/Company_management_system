package hu.progmatic.company_management_system.repositories;

import hu.progmatic.company_management_system.models.Product;
import hu.progmatic.company_management_system.models.ProductCondition;
import hu.progmatic.company_management_system.models.Warehouse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {

/*    List<Product> findByConditionEquals(ProductCondition productCondition);
    List<Product> findByWarehouseEquals(Warehouse warehouse);*/
    List<Product> findAll();
}

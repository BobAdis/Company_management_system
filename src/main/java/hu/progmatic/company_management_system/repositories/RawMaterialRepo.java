package hu.progmatic.company_management_system.repositories;


import hu.progmatic.company_management_system.models.RawMaterial;
import hu.progmatic.company_management_system.models.Warehouse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RawMaterialRepo extends CrudRepository<RawMaterial, Long> {

    List<RawMaterial> findAll();

    List<RawMaterial> findAllByShippingInNull();
    List<RawMaterial> getRawMaterialByWarehouse(Warehouse warehouse);
}

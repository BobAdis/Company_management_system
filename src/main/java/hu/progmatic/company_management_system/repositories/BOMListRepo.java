package hu.progmatic.company_management_system.repositories;


import hu.progmatic.company_management_system.models.BOMList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BOMListRepo extends CrudRepository<BOMList, Long> {

    List<BOMList> findAll();
}

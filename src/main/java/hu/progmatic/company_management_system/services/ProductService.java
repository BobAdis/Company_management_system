package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.Product;
import hu.progmatic.company_management_system.models.ProductCondition;
import hu.progmatic.company_management_system.models.Warehouse;
import hu.progmatic.company_management_system.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Iterable<Product> getAllProduct() {
        return productRepo.findAll();
    }

    public List<Product> getAllRawMaterialProducts () {
        return productRepo.findByProductConditionEquals(ProductCondition.RAWMATERIAL);
    }

    public List<Product> getAllFinishedProducts () {
        return productRepo.findByProductConditionEquals(ProductCondition.FINISHED);
    }

    public List<Product> getAllInboundWarehouseProducts () {
        return productRepo.findByWarehouseEquals(Warehouse.INBOUND);
    }

    public List<Product> getAllOutboundWarehouseProducts() {
        return productRepo.findByWarehouseEquals(Warehouse.OUTBOND);
    }

    public List<Product> getAllWorkstationStorageProducts () {
        return productRepo.findByWarehouseEquals(Warehouse.WORKSTATIONS);
    }

    public List<Product> getAllRejectWarehouseProducts () {
        return productRepo.findByWarehouseEquals(Warehouse.REJECT);
    }

}

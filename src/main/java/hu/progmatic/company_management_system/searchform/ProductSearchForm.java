package hu.progmatic.company_management_system.searchform;

import hu.progmatic.company_management_system.models.ProductCondition;

public class ProductSearchForm {

    private String name;

    private Integer serialNumber;

    private ProductCondition productCondition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ProductCondition getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(ProductCondition productCondition) {
        this.productCondition = productCondition;
    }
}

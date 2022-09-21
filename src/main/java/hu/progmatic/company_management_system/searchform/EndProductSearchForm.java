package hu.progmatic.company_management_system.searchform;

import hu.progmatic.company_management_system.models.ProducedProduct;
import hu.progmatic.company_management_system.models.ShippingOut;

public class EndProductSearchForm {

    private String producedProduct;
    private Integer serialNumber;
    private ShippingOut shippingOut;

    public String getProducedProduct() {
        return producedProduct;
    }

    public void setProducedProduct(String producedProduct) {
        this.producedProduct = producedProduct;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ShippingOut getShippingOut() {
        return shippingOut;
    }

    public void setShippingOut(ShippingOut shippingOut) {
        this.shippingOut = shippingOut;
    }
}

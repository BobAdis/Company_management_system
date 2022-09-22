package hu.progmatic.company_management_system.searchform;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EndProductSearchForm {

    private String producedProduct;
    private Integer serialNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shippingOut;

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

    public LocalDate getShippingOut() {
        return shippingOut;
    }

    public void setShippingOut(LocalDate shippingOut) {
        this.shippingOut = shippingOut;
    }
}

package hu.progmatic.company_management_system.searchform;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ShippingOutSearchForm {
    private String buyer;

    private String endProductName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getEndProductName() {
        return endProductName;
    }

    public void setEndProductName(String endProductName) {
        this.endProductName = endProductName;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

}

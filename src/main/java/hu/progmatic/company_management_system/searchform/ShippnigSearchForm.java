package hu.progmatic.company_management_system.searchform;

import hu.progmatic.company_management_system.models.Partner;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ShippnigSearchForm {

    private String buyer;
    private String seller;
    private String rawMaterialName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getRawMaterialName() {
        return rawMaterialName;
    }

    public void setRawMaterialName(String rawMaterialName) {
        this.rawMaterialName = rawMaterialName;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}

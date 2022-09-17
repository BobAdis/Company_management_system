package hu.progmatic.company_management_system.searchform;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ShippnigSearchForm {

    private String seller;
    private String rawMaterialName;
    private LocalDate localDate;

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

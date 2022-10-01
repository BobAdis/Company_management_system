package hu.progmatic.company_management_system.searchform;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class RawMaterialSearchForm {

    private String ingredient;
    private Integer SARZSNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shippingIn;

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getSARZSNumber() {
        return SARZSNumber;
    }

    public void setSARZSNumber(Integer SARZSNumber) {
        this.SARZSNumber = SARZSNumber;
    }

    public LocalDate getShippingIn() {
        return shippingIn;
    }

    public void setShippingIn(LocalDate shippingIn) {
        this.shippingIn = shippingIn;
    }

}

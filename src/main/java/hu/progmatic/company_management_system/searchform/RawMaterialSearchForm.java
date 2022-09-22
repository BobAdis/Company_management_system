package hu.progmatic.company_management_system.searchform;

import hu.progmatic.company_management_system.models.Ingredient;
import hu.progmatic.company_management_system.models.ShippingIn;

public class RawMaterialSearchForm {

    private Ingredient ingredient;
    private Integer SARZSNumber;
    private ShippingIn shippingIn;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getSARZSNumber() {
        return SARZSNumber;
    }

    public void setSARZSNumber(Integer SARZSNumber) {
        this.SARZSNumber = SARZSNumber;
    }

    public ShippingIn getShippingIn() {
        return shippingIn;
    }

    public void setShippingIn(ShippingIn shippingIn) {
        this.shippingIn = shippingIn;
    }
}

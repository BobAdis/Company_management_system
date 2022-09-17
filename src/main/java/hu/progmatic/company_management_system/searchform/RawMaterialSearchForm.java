package hu.progmatic.company_management_system.searchform;

import hu.progmatic.company_management_system.models.Ingredient;
import hu.progmatic.company_management_system.models.Shipping;

public class RawMaterialSearchForm {

    private Ingredient ingredient;
    private Integer SARZSNumber;
    private Shipping shipping;

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

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }
}

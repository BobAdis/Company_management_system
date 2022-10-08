package hu.progmatic.company_management_system.models.production;

import hu.progmatic.company_management_system.models.sales.ShippingIn;

import javax.persistence.*;

@Entity
public class RawMaterial {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Ingredient ingredient;
    private Integer SARZSNumber;
    private Integer unitPrice;
    private Integer quantity;
    @ManyToOne
    private ShippingIn shippingIn;
    @Enumerated(EnumType.STRING)
    private Warehouse warehouse;

    public RawMaterial() {
    }

    public RawMaterial(Ingredient ingredient, Integer SARZSNumber, Integer unitPrice, Integer quantity, ShippingIn shippingIn, Warehouse warehouse) {
        this.ingredient = ingredient;
        this.SARZSNumber = SARZSNumber;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.shippingIn = shippingIn;
        this.warehouse = warehouse;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShippingIn getShippingIn() {
        return shippingIn;
    }

    public void setShippingIn(ShippingIn shippingIn) {
        this.shippingIn = shippingIn;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}

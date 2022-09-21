package hu.progmatic.company_management_system.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EndProduct {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private ProducedProduct producedProduct;
    private Integer serialNumber;
    private Integer unitPrice;
    private Integer quantity;
    @ManyToOne
    private ShippingOut shippingOut;

    public EndProduct() {
    }

    public EndProduct(ProducedProduct producedProduct, Integer serialNumber, Integer unitPrice, Integer quantity, ShippingOut shippingOut) {
        this.producedProduct = producedProduct;
        this.serialNumber = serialNumber;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.shippingOut = shippingOut;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ProducedProduct getProducedProduct() {
        return producedProduct;
    }

    public void setProducedProduct(ProducedProduct producedProduct) {
        this.producedProduct = producedProduct;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
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

    public ShippingOut getShippingOut() {
        return shippingOut;
    }

    public void setShippingOut(ShippingOut shippingOut) {
        this.shippingOut = shippingOut;
    }
}

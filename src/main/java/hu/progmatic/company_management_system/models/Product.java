package hu.progmatic.company_management_system.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

/*    @NotNull
    @Size(min=2, max=30)*/
    private String name;

    private int serialNumber;

    private double unitPrice;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProductCondition productCondition;


    public Product() {
    }

    public Product(String name, int serialNumber, double unitPrice, String description, ProductCondition productCondition) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.unitPrice = unitPrice;
        this.description = description;
        this.productCondition = productCondition;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCondition getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(ProductCondition productCondition) {
        this.productCondition = productCondition;
    }
}

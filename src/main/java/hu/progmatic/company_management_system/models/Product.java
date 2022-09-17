package hu.progmatic.company_management_system.models;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer serialNumber;

    private Integer Price;

    @OneToOne
    private BOMList bomList;



    public Product() {
    }

    public Product(String name, Integer serialNumber, Integer price) {
        this.name = name;
        this.serialNumber = serialNumber;
        Price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }
}

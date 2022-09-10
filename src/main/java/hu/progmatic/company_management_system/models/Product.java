package hu.progmatic.company_management_system.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int serialNumber;

    private double unitPrice;

    private String description;

    private ProductCondition productCondition;

    private Warehouse warehouse;


    public Product() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

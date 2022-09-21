package hu.progmatic.company_management_system.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ShippingOut {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Partner buyer;
    @OneToMany(mappedBy = "shippingOut")
    private List<EndProduct> endProducts;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    public ShippingOut() {
    }

    public ShippingOut(Partner buyer, LocalDate localDate, List<EndProduct> endProducts) {
        this.buyer = buyer;
        this.endProducts = endProducts;
        this.localDate = localDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Partner getBuyer() {
        return buyer;
    }

    public void setBuyer(Partner buyer) {
        this.buyer = buyer;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<EndProduct> getEndProducts() {
        return endProducts;
    }

    public void setEndProducts(List<EndProduct> endProducts) {
        this.endProducts = endProducts;
    }
}

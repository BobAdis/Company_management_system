package hu.progmatic.company_management_system.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ShippingIn {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Partner seller;
    @OneToMany (mappedBy = "shippingIn")
    private List<RawMaterial> rawMaterials;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    public ShippingIn() {
    }

    public ShippingIn(Partner seller, LocalDate localDate, List<RawMaterial> rawMaterials) {
        this.seller = seller;
        this.localDate = localDate;
        this.rawMaterials = rawMaterials;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Partner getSeller() {
        return seller;
    }

    public void setSeller(Partner seller) {
        this.seller = seller;
    }

    public List<RawMaterial> getRawMaterials() {
        return rawMaterials;
    }

    public void setRawMaterials(List<RawMaterial> rawMaterials) {
        this.rawMaterials = rawMaterials;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}

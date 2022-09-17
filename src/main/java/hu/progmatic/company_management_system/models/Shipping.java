package hu.progmatic.company_management_system.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Shipping {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Partner buyer;
    @OneToOne
    private Partner seller;
    @OneToMany (mappedBy = "shipping")
    private List<RawMaterial> rawMaterials;
    private LocalDate localDate;

    public void getxyz (List<RawMaterial> rawMaterial) {
        for (RawMaterial rawMaterial1 : rawMaterial) {
            String name = rawMaterial1.getIngredient().getName();
            System.out.println(name);
        }
    }

    public Shipping() {
    }

    public Shipping(Partner buyer, Partner seller, LocalDate localDate, List<RawMaterial> rawMaterials) {
        this.buyer = buyer;
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

    public Partner getBuyer() {
        return buyer;
    }

    public void setBuyer(Partner buyer) {
        this.buyer = buyer;
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

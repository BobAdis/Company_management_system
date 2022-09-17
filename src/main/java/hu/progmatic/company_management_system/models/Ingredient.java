package hu.progmatic.company_management_system.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String unit;
    @ManyToOne
    private BOMList bomList;
    @OneToMany(mappedBy = "ingredient")
    private List<RawMaterial> rawMaterial;

    public Ingredient() {
    }

    public Ingredient(String name, String description, String unit) {
        this.name = name;
        this.description = description;
        this.unit = unit;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BOMList getBomList() {
        return bomList;
    }

    public void setBomList(BOMList bomList) {
        this.bomList = bomList;
    }

    public List<RawMaterial> getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(List<RawMaterial> rawMaterial) {
        this.rawMaterial = rawMaterial;
    }
}

package hu.progmatic.company_management_system.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProducedProduct {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne
    private BOMList bomList;
    @OneToMany(mappedBy = "producedProduct")
    private List<EndProduct> endProduct;


    public ProducedProduct() {
    }



    public ProducedProduct(String name) {
        this.name = name;
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

    public BOMList getBomList() {
        return bomList;
    }

    public void setBomList(BOMList bomList) {
        this.bomList = bomList;
    }

    public List<EndProduct> getEndProduct() {
        return endProduct;
    }

    public void setEndProduct(List<EndProduct> endProduct) {
        this.endProduct = endProduct;
    }
}

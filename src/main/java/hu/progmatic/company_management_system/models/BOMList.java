package hu.progmatic.company_management_system.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class BOMList {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    private ProducedProduct producedProduct;
    @OneToMany(mappedBy = "bomList")
    private List<Ingredient> ingredients;

    public BOMList() {
    }

    public BOMList(String name, ProducedProduct producedProduct, List<Ingredient> ingredients) {
        this.name = name;
        this.producedProduct = producedProduct;
        this.ingredients = ingredients;
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

    public ProducedProduct getProduct() {
        return producedProduct;
    }

    public void setProduct(ProducedProduct producedProduct) {
        this.producedProduct = producedProduct;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}

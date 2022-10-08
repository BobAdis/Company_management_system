package hu.progmatic.company_management_system.models.production;

import javax.persistence.*;
import java.util.List;

@Entity
public class BOMList {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne (mappedBy = "bomList")
    private ProducedProduct producedProduct;
    @OneToMany(mappedBy = "bomList")
    private List<Ingredient> ingredients;

    private String description;

    public BOMList() {
    }

    public BOMList(String name, ProducedProduct producedProduct, List<Ingredient> ingredients, String description) {
        this.name = name;
        this.producedProduct = producedProduct;
        this.ingredients = ingredients;
        this.description = description;
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

    public ProducedProduct getProducedProduct() {
        return producedProduct;
    }

    public void setProducedProduct(ProducedProduct producedProduct) {
        this.producedProduct = producedProduct;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

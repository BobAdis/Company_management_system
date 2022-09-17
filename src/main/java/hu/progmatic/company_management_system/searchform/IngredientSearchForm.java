package hu.progmatic.company_management_system.searchform;

import hu.progmatic.company_management_system.models.BOMList;

public class IngredientSearchForm {

    private String name;
    private BOMList bomList;


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
}

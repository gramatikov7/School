package com.example.restaurantitaly.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private String categoryType;
    private Set<Products> products;

    public Category() {

    }

    @NotNull
    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }


    @OneToMany(mappedBy = "category")
    public Set<Products> getProducts() {
        return products;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }
}

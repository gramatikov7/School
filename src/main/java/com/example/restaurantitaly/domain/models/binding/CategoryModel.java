package com.example.restaurantitaly.domain.models.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CategoryModel extends BaseBindingModel {

    private String categoryType;

    public CategoryModel() {

    }


    @NotNull(message = "Category can not be empty")
    @Length(min = 3 , max = 20 , message = "Category must ne between 4 and 20 symbols")
    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}

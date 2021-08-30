package com.example.restaurantitaly.domain.models.service;

public class CategoryServiceModel extends BaseServiceModel{

    private String categoryType;

    public CategoryServiceModel() {
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}

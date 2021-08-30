package com.example.restaurantitaly.domain.models.service;

public class ProductServiceModel extends BaseServiceModel {

    private String name;
    private double price;
    private double quantity;
    private String description;
    private String imageUrl;
    private boolean isDeleted;
    private CategoryServiceModel categoryType;

    public ProductServiceModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public CategoryServiceModel getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryServiceModel categoryType) {
        this.categoryType = categoryType;
    }
}

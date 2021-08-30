package com.example.restaurantitaly.domain.models.service;


public class CartServiceModel {

    private String name;
    private double price;
    private double quantity;
    private String description;
    private String imageUrl;
    private CategoryServiceModel category;
    private double shopQuantity;


    public CartServiceModel() {

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

    public CategoryServiceModel getCategory() {
        return category;
    }

    public void setCategory(CategoryServiceModel category) {
        this.category = category;
    }

    public double getShopQuantity() {
        return shopQuantity;
    }

    public void setShopQuantity(double shopQuantity) {
        this.shopQuantity = shopQuantity;
    }
}

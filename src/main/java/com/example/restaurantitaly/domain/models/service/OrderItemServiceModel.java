package com.example.restaurantitaly.domain.models.service;


public class OrderItemServiceModel extends BaseServiceModel {

    private double totalPrice;
    private int totalQuantity;
    private ProductServiceModel products;

    public OrderItemServiceModel() {
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public ProductServiceModel getProducts() {
        return products;
    }

    public void setProducts(ProductServiceModel products) {
        this.products = products;
    }
}

package com.example.restaurantitaly.domain.models.binding;

public class OrderItemModel extends BaseBindingModel {

    private double totalPrice;
    private double totalQuantity;
    private ProductModel products;

    public OrderItemModel() {
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public ProductModel getProducts() {
        return products;
    }

    public void setProducts(ProductModel products) {
        this.products = products;
    }
}

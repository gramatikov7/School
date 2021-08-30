package com.example.restaurantitaly.domain.models.view;


import java.util.List;

public class CartViewModel {
    private ProductViewModel product;
    private double shopQuantity;
    private double totalPrice;

    public CartViewModel() {
    }

    public ProductViewModel getProduct() {
        return product;
    }

    public void setProduct(ProductViewModel product) {
        this.product = product;
    }

    public double getShopQuantity() {
        return shopQuantity;
    }

    public void setShopQuantity(double shopQuantity) {
        this.shopQuantity = shopQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

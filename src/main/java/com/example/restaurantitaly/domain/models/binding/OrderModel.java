package com.example.restaurantitaly.domain.models.binding;

import java.util.List;

public class OrderModel extends BaseBindingModel {

    private UserModel user;
    private List<OrderItemModel> products;

    public OrderModel() {

    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<OrderItemModel> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItemModel> products) {
        this.products = products;
    }
}

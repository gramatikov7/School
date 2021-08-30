package com.example.restaurantitaly.domain.models.service;

import java.util.List;

public class OrderServiceModel {

    private UserRegisterServiceModel user;
    private List<OrderItemServiceModel> items;

    public OrderServiceModel() {

    }


    public UserRegisterServiceModel getUser() {
        return user;
    }

    public void setUser(UserRegisterServiceModel user) {
        this.user = user;
    }

    public List<OrderItemServiceModel> getItems() {
        return items;
    }

    public void setItems(List<OrderItemServiceModel> items) {
        this.items = items;
    }
}

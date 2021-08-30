package com.example.restaurantitaly.domain.models.view;

import com.example.restaurantitaly.domain.models.binding.OrderItemModel;
import com.example.restaurantitaly.domain.models.binding.UserModel;

import java.util.List;

public class OrderViewModel {
    private int id;
    private UserModel user;
    private List<OrderItemModel> items;

    public OrderViewModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<OrderItemModel> getItems() {
        return items;
    }

    public void setItems(List<OrderItemModel> items) {
        this.items = items;
    }
}

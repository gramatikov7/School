package com.example.restaurantitaly.domain.models.binding;

public abstract class BaseBindingModel {
    private int id;

    public BaseBindingModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.example.restaurantitaly.domain.models.service;

public abstract class BaseServiceModel {
    private int id;

    public BaseServiceModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

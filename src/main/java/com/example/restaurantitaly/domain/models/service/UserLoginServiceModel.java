package com.example.restaurantitaly.domain.models.service;

public class UserLoginServiceModel extends BaseServiceModel {

    private String username;
    private String password;

    public UserLoginServiceModel() {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

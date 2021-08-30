package com.example.restaurantitaly.domain.models.service;

import java.util.List;
import java.util.Set;

public class UserServiceModel extends BaseServiceModel {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private List<RoleServiceModel> authorities;
    private List<OrderServiceModel> orders;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleServiceModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<RoleServiceModel> authorities) {
        this.authorities = authorities;
    }

    public List<OrderServiceModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderServiceModel> orders) {
        this.orders = orders;
    }
}

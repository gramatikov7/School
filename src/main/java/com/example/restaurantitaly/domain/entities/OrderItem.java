package com.example.restaurantitaly.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    private double totalPrice;
    private int totalQuantity;
    private Products products;


    public OrderItem() {

    }

    @Column
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Column
    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }


    @ManyToOne
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }


//
//    @ManyToMany(mappedBy = "items")
//    public Set<Orders> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Set<Orders> orders) {
//        this.orders = orders;
//    }
}

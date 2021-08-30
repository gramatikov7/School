package com.example.restaurantitaly.domain.entities;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "orders")
public class Orders extends BaseEntity {

    private User user;
    private Set<OrderItem> items;

    public Orders() {

    }

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false , referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany( targetEntity = OrderItem.class , cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
    @JoinTable( name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id" , referencedColumnName = "id"))
    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }
}

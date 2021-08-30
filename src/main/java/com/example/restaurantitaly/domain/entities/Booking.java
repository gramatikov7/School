package com.example.restaurantitaly.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity{

    private String name;
    private String email;
    private String mobile;
    private String date;
    private String time;
    private int guests;
    private User user;


    public Booking() {

    }


    @Column(name = "name" , nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email",  nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "mobile", nullable = false)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @Column(name = "date", nullable = false)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @Column(name = "time" ,  nullable = false)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Column(name = "gusets" , nullable = false)
    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

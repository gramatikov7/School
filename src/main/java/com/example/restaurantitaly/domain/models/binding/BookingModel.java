package com.example.restaurantitaly.domain.models.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class BookingModel extends BaseBindingModel {
    private String name;
    private String email;
    private String mobile;
    private String date;
    private String time;
    private int guests;

    public BookingModel() {

    }


    @NotNull
    @Length(min = 3 , max = 20 , message = "Name must be between 3 and 20 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @NotNull
    @Length(min = 5 , max = 20 , message = "Email must be between 3 and 20 characters.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @NotNull
    @Length(min = 5 , max = 20 , message = "Mobile must be between 3 and 10 characters.")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    @NotNull
    @Length(min = 5 , max = 20 , message = "Date must be between 3 and 20 characters.")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @NotNull
    @Length(min = 5 , max = 20 , message = "Time must be not empty.")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @NotNull
    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }
}

package com.example.restaurantitaly.domain.models.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import static com.example.restaurantitaly.common.Constants.*;


public class UserLoginModel extends BaseBindingModel {

    private String username;
    private String password;

    public UserLoginModel() {

    }


    @NotEmpty(message = EMPTY_USERNAME_MESSAGE)
    @NotNull(message = NULL_USERNAME_MESSAGE)
    @Length(min = 5 , max = 20 , message = WRONG_USERNAME_LENGTH_MESSAGE)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = EMPTY_PASSWORD_MESSAGE)
    @NotNull(message = NULL_PASSWORD_MESSAGE)
    @Length(min = 4 , max = 20 , message = WRONG_PASSWORD_LENGTH_MESSAGE)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

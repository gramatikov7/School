package com.example.restaurantitaly.services;


import com.example.restaurantitaly.domain.models.service.UserRegisterServiceModel;
import com.example.restaurantitaly.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    void register(UserRegisterServiceModel userRegisterServiceModel);

    UserRegisterServiceModel getUserById(int id);

    UserServiceModel getUserByUsername(String name);

    boolean checkIfUserAlreadyExist(String username , String email);

}

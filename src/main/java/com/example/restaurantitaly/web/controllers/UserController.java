package com.example.restaurantitaly.web.controllers;

import com.example.restaurantitaly.domain.models.binding.UserLoginModel;
import com.example.restaurantitaly.domain.models.binding.UserRegisterModel;
import com.example.restaurantitaly.domain.models.service.UserRegisterServiceModel;
import com.example.restaurantitaly.services.UserService;
import com.example.restaurantitaly.services.validation.UserRegisterValidator;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRegisterValidator validator;

    public UserController(UserService userService, ModelMapper modelMapper, UserRegisterValidator validator) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }


    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(@ModelAttribute(name = "model") UserRegisterModel userRegisterModel) {


        return view("user/register");

    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerUser(@ModelAttribute(name = "model") UserRegisterModel userRegisterModel,
                                     BindingResult bindingResult) {


        validator.validate(userRegisterModel, bindingResult);


        if (bindingResult.hasErrors()) {

            return view("user/register");
        }


        this.userService.register(this.modelMapper.map(userRegisterModel, UserRegisterServiceModel.class));


        return redirect("/users/login");


    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login() {


        return view("user/login");
    }



}

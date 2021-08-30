package com.example.restaurantitaly.web.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public ModelAndView index() {

        return view("home/index");

    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home() {


        return view("home/home");

    }
}

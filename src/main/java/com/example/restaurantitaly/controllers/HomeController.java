package com.example.restaurantitaly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {


    @GetMapping("/")
    public ModelAndView homePage() {

        return view("home/home");
    }


}

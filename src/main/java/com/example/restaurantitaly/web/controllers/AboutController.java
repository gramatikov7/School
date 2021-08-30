package com.example.restaurantitaly.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/about")
public class AboutController extends BaseController {

    @GetMapping("/us")
    public ModelAndView about(){


        return view("about");
    }

    @GetMapping("/contact")
    public ModelAndView contact(){



        return view("contact");
    }


}

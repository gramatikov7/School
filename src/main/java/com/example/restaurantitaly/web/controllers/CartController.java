package com.example.restaurantitaly.web.controllers;

import com.example.restaurantitaly.domain.models.service.OrderItemServiceModel;
import com.example.restaurantitaly.services.OrderService;
import com.example.restaurantitaly.services.ProductService;
import com.example.restaurantitaly.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;


    public CartController(ModelMapper modelMapper, ProductService productService, OrderService orderService, UserService userService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }



    @GetMapping("/cart")
    public ModelAndView allOrders(@Valid ModelAndView modelAndView){

        List<OrderItemServiceModel> order = orderService.getCurrentOrder();

        modelAndView.addObject("order", order);


        return view("cart/cart", modelAndView);

    }

}




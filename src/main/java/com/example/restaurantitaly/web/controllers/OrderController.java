package com.example.restaurantitaly.web.controllers;


import com.example.restaurantitaly.domain.models.binding.OrderItemModel;
import com.example.restaurantitaly.domain.models.service.OrderItemServiceModel;
import com.example.restaurantitaly.domain.models.service.ProductServiceModel;
import com.example.restaurantitaly.domain.models.service.UserServiceModel;
import com.example.restaurantitaly.domain.models.view.OrderViewModel;
import com.example.restaurantitaly.services.OrderService;
import com.example.restaurantitaly.services.ProductService;
import com.example.restaurantitaly.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderController extends BaseController {


    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final UserService userService;


    public OrderController(OrderService orderService, ModelMapper modelMapper, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.userService = userService;

    }



    @GetMapping("/mine")
    public ModelAndView getMyOrders(){

        return view("order/orders");

    }

    @GetMapping("/details/{id}")
    public ModelAndView detailsOrder(@PathVariable int id , ModelAndView modelAndView){


        OrderViewModel order = orderService.getOrderById(id);
//        OrderViewModel order = modelMapper.map(orderById,  OrderViewModel.class);
        double sum = order.getItems().stream().mapToDouble(a -> a.getTotalPrice()).sum();

        modelAndView.addObject("order" , order);
        modelAndView.addObject("sum" , sum);

        return view("order/order-details" , modelAndView);

    }

    @GetMapping("/all")
    public ModelAndView getAllOrders(ModelAndView modelAndView){


        List<OrderViewModel> allOrders = orderService.getAllOrders();

        modelAndView.addObject("allOrders" , allOrders);

        return view("order/all-orders" , modelAndView);

    }



    @PostMapping("/add")
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView addToOrder(@Valid @ModelAttribute(name = "model") OrderItemModel orderItemModel ,
                                   int id , Principal principal){

        ProductServiceModel productById = productService.getProductById(id);

        OrderItemServiceModel orderItemServiceModel = modelMapper.map(orderItemModel, OrderItemServiceModel.class);
        orderItemServiceModel.setProducts(productById);
        orderItemServiceModel.setTotalPrice(productById.getPrice() * orderItemModel.getTotalQuantity());

        orderService.createOrder(principal.getName() , orderItemServiceModel);



        return redirect("/menu/all");
    }

    @PostMapping("/checkout")
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView saveOrder(Principal principal) {

    orderService.saveOrder(principal.getName());

    return redirect("/home");

    }



    @GetMapping("/remove/{name}")
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView delItem(@PathVariable String name , ModelAndView modelAndView){

        orderService.delItem(name);

        List<OrderItemServiceModel> order = orderService.getCurrentOrder();

        modelAndView.addObject("order", order);


        return redirect("/cart/cart");

    }

    @GetMapping("/fetch")
    @ResponseBody
    public double finalPrice(){

        return orderService.getCurrentOrder().stream().mapToDouble(OrderItemServiceModel::getTotalPrice).sum();
    }

    @GetMapping("/myorders")
    @ResponseBody
    public List<OrderViewModel> myOrder(Principal principal){

        UserServiceModel userByUsername = userService.getUserByUsername(principal.getName());

        List<OrderViewModel> orderByUserId = orderService.getOrderByUserId(userByUsername.getId());

        List<OrderViewModel> collect =
                orderByUserId.stream().map(a -> modelMapper.map(a, OrderViewModel.class)).collect(Collectors.toList());



        return collect;

    }

}

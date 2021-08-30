package com.example.restaurantitaly.services.impl;


import com.example.restaurantitaly.domain.entities.Orders;
import com.example.restaurantitaly.domain.entities.User;
import com.example.restaurantitaly.domain.models.service.OrderItemServiceModel;
import com.example.restaurantitaly.domain.models.service.OrderServiceModel;
import com.example.restaurantitaly.domain.models.service.UserServiceModel;
import com.example.restaurantitaly.domain.models.view.OrderViewModel;
import com.example.restaurantitaly.repositories.OrderRepository;
import com.example.restaurantitaly.services.OrderItemService;
import com.example.restaurantitaly.services.OrderService;
import com.example.restaurantitaly.services.ProductService;
import com.example.restaurantitaly.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private List<OrderItemServiceModel> items;


    public OrderServiceImpl(ModelMapper modelMapper, ProductService productService, UserService userService, OrderRepository orderRepository, OrderItemService orderItemService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;

        items = new ArrayList<>();
    }

    @Override
    public void createOrder(String username, OrderItemServiceModel orderItemServiceModel) {

        boolean flag = false;

        if(items.size() == 0){

            items.add(orderItemServiceModel);
            return;

        }else {
            for (OrderItemServiceModel item : items) {
                if(item.getProducts().getCategoryType().equals(orderItemServiceModel.getProducts().getCategoryType()) &&
                        item.getProducts().getName().equals(orderItemServiceModel.getProducts().getName())){
                    item.setTotalQuantity(item.getTotalQuantity() + orderItemServiceModel.getTotalQuantity());
                    flag = true;
                }
            }
        }

        if(!flag){
            items.add(orderItemServiceModel);
        }



    }

    @Override
    public void saveOrderInDB(String username) {

        UserServiceModel userByUsername = userService.getUserByUsername(username);


        OrderServiceModel orderServiceModel = modelMapper.map(userByUsername, OrderServiceModel.class);
        orderServiceModel.setItems(items);

        Orders orders = modelMapper.map(orderServiceModel, Orders.class);



        orderRepository.save(orders);

        items = new ArrayList<>();

    }

    @Override
    public List<OrderItemServiceModel> getCurrentOrder() {

        return items;

    }

    @Override
    public List<OrderViewModel> getOrderByUserId(int id) {


        return orderRepository.findAllByUserId(id).stream()
                .map(a -> modelMapper.map(a , OrderViewModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public void delItem(String name) {

        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getProducts().getName().equals(name)){
                items.remove(i);
                break;
            }
        }


    }

    @Override
    public void saveOrder(String username) {

        UserServiceModel userByUsername = userService.getUserByUsername(username);
        User user = modelMapper.map(userByUsername, User.class);
        OrderServiceModel orderServiceModel = modelMapper.map(user, OrderServiceModel.class);
        orderServiceModel.setItems(items);
        Orders orders = modelMapper.map(orderServiceModel, Orders.class);


        orderItemService.saveOrderItemInDb(items);

        orderRepository.saveAndFlush(orders);

        items = new ArrayList<>();

    }

    @Override
    public OrderViewModel getOrderById(int id) {

        Orders order = orderRepository.getById(id);

        OrderViewModel map = modelMapper.map(order, OrderViewModel.class);

        return map;

    }

    @Override
    public List<OrderViewModel> getAllOrders() {

        List<Orders> all = orderRepository.findAll();
        return all.stream().map(a -> modelMapper.map(a , OrderViewModel.class)).collect(Collectors.toList());

    }

    @Override
    public void delItemById(int id) {

        orderRepository.deleteById(id);
    }


}

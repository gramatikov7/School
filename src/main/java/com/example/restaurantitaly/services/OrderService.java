package com.example.restaurantitaly.services;


import com.example.restaurantitaly.domain.models.service.OrderItemServiceModel;
import com.example.restaurantitaly.domain.models.view.OrderViewModel;

import java.util.List;

public interface OrderService {

    void createOrder(String username, OrderItemServiceModel orderItemServiceModel);

    void saveOrderInDB(String username);

    List<OrderItemServiceModel> getCurrentOrder();

    List<OrderViewModel> getOrderByUserId(int id);

    void delItem(String name);

    void saveOrder(String username);

    OrderViewModel getOrderById(int id);

    List<OrderViewModel> getAllOrders();

    void delItemById(int id);

}

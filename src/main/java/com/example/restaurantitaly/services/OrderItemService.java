package com.example.restaurantitaly.services;

import com.example.restaurantitaly.domain.models.service.OrderItemServiceModel;

import java.util.List;

public interface OrderItemService {

    List<OrderItemServiceModel> getAllOrderItems();


    void saveOrderItemInDb(List<OrderItemServiceModel> orderItemServiceModel);

    void deleteItemById(int id);
}


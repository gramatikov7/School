package com.example.restaurantitaly.services.impl;


import com.example.restaurantitaly.domain.entities.OrderItem;
import com.example.restaurantitaly.domain.models.service.OrderItemServiceModel;
import com.example.restaurantitaly.repositories.OrderItemRepository;
import com.example.restaurantitaly.services.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ModelMapper modelMapper) {
        this.orderItemRepository = orderItemRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<OrderItemServiceModel> getAllOrderItems() {

       return orderItemRepository.findAll()
                .stream()
                .map( a-> modelMapper.map(a , OrderItemServiceModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public void saveOrderItemInDb(List<OrderItemServiceModel> orderItemServiceModel) {

        List<OrderItem> collect = orderItemServiceModel.stream().map(a -> modelMapper.map(a, OrderItem.class)).collect(Collectors.toList());

        for (OrderItem orderItem : collect) {
            orderItemRepository.save(orderItem);
        }


    }

    @Override
    public void deleteItemById(int id) {
        orderItemRepository.deleteById(id);
    }


}

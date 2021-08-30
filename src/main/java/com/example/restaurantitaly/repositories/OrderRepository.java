package com.example.restaurantitaly.repositories;

import com.example.restaurantitaly.domain.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {


    List<Orders> findAllByUserId(int id);
}

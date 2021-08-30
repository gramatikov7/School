package com.example.restaurantitaly.repositories;

import com.example.restaurantitaly.domain.entities.Booking;
import com.example.restaurantitaly.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByUser(User user);

}

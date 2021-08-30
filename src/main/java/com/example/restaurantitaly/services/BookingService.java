package com.example.restaurantitaly.services;

import com.example.restaurantitaly.domain.models.service.BookingServiceModel;

import java.util.List;

public interface BookingService {
    void createBooking(BookingServiceModel bookingServiceModel , String username);

    List<BookingServiceModel> getAllBookingsByUser(String username);


    List<BookingServiceModel> getAllBookings();
}

package com.example.restaurantitaly.services.impl;


import com.example.restaurantitaly.domain.entities.Booking;
import com.example.restaurantitaly.domain.entities.User;
import com.example.restaurantitaly.domain.models.service.BookingServiceModel;
import com.example.restaurantitaly.domain.models.service.UserServiceModel;
import com.example.restaurantitaly.repositories.BookingRepository;
import com.example.restaurantitaly.services.BookingService;
import com.example.restaurantitaly.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, UserService userService, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createBooking(BookingServiceModel bookingServiceModel, String username) {


        UserServiceModel userByUsername = userService.getUserByUsername(username);

        Booking map = modelMapper.map(bookingServiceModel, Booking.class);
        User user = modelMapper.map(userByUsername, User.class);
        map.setUser(user);
        bookingRepository.save(map);

    }

    @Override
    public List<BookingServiceModel> getAllBookingsByUser(String username) {

        UserServiceModel userByUsername = userService.getUserByUsername(username);
        User user = modelMapper.map(userByUsername, User.class);

        List<Booking> all = bookingRepository.findAllByUser(user);
        List<BookingServiceModel> collect =
                all.
                        stream()
                        .map(a -> modelMapper.map(a, BookingServiceModel.class))
                        .collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<BookingServiceModel> getAllBookings() {

        List<Booking> all = bookingRepository.findAll();

        return all.
                stream()
                .map(a -> modelMapper.map(a, BookingServiceModel.class))
                .collect(Collectors.toList());
    }


}

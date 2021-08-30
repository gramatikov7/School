package com.example.restaurantitaly.web.controllers;


import com.example.restaurantitaly.domain.models.binding.BookingModel;
import com.example.restaurantitaly.domain.models.service.BookingServiceModel;
import com.example.restaurantitaly.domain.models.view.BookingViewModel;
import com.example.restaurantitaly.services.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/booking")
public class BookingController extends BaseController {

    private final BookingService bookingService;
    private final ModelMapper modelMapper;

    public BookingController(BookingService bookingService, ModelMapper modelMapper) {
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView newBooking(@ModelAttribute(name = "model") BookingModel bookingModel) {

        return view("booking/booking");
    }




    @PostMapping("/add")
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView addBooking(@ModelAttribute(name = "model") @Valid BookingModel bookingModel , BindingResult bindingResult ,
                                   Principal principal){


        if(bindingResult.hasErrors()){

            return view("booking/booking");
        }

        BookingServiceModel bookingServiceModel = modelMapper.map(bookingModel, BookingServiceModel.class);


        bookingService.createBooking(bookingServiceModel , principal.getName());

        return redirect("/home");

    }

    @GetMapping("/fetch")
    @ResponseBody
    public List<BookingViewModel> fetchAllBookingForUser(Principal principal) {

        List<BookingServiceModel> allBookings = bookingService.getAllBookingsByUser(principal.getName());

        return allBookings.stream().map(a -> modelMapper.map(a , BookingViewModel.class)).collect(Collectors.toList());


    }

    @GetMapping("/fetchAll")
    @ResponseBody
    public List<BookingViewModel> fetchAllBooking() {

        List<BookingServiceModel> allBookings = bookingService.getAllBookings();

        return allBookings.stream().map(a -> modelMapper.map(a , BookingViewModel.class)).collect(Collectors.toList());


    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    private ModelAndView allBooking(){



        return view("booking/all-booking");
    }


    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    private ModelAndView allBookingForUser(){



        return view("booking/all-booking-for-user");
    }


}

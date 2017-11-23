package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public void getBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping("/bookings")
    public String getAllBooking(Model model){
        model.addAttribute("bookings", bookingService.getAllBooking());
        return "admin/booking/index";
    }

    @RequestMapping("/bookings/{id}")
    public String getBooking(Model model, @PathVariable ("id") int bookingId){
        model.addAttribute("booking", bookingService.getBooking(bookingId));
        return "admin/booking/index";
    }


}

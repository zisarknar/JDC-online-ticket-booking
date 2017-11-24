package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.domains.Booking;
import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.domains.Trip;
import com.solt.jdc.boot.services.BookingService;
import com.solt.jdc.boot.services.CustomerService;
import com.solt.jdc.boot.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BookingController{

    @Autowired
    private BookingService bookingService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TripService tripService;


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

    @RequestMapping(value = "/bookings/add", method = RequestMethod.GET)
    public String addBooking(Model model){
        Booking booking = new Booking();
        model.addAttribute("allTrip", tripService.getAllTrips());
        model.addAttribute("allCustomer", customerService.getAllCustomers());
        model.addAttribute("newBooking", booking);
        return "admin/booking/addNew";
    }

    @RequestMapping(value = "/bookings/add", method = RequestMethod.POST)
    public String processAddBooking(@ModelAttribute ("newBooking") Booking newBooking){
        bookingService.saveBooking(newBooking);
        return "redirect:/bookings";
    }

    @RequestMapping(value = "/booking/update/{id}", method = RequestMethod.GET)
    public String updateBooking(@PathVariable("id") int bookingId, Model model){
        model.addAttribute("allTrip", tripService.getAllTrips());
        model.addAttribute("allCustomer", customerService.getAllCustomers());
        model.addAttribute("updatedBooking", bookingService.getBooking(bookingId));
        return "admin/booking/update";
    }

    @RequestMapping(value = "/booking/update/{id}", method = RequestMethod.POST)
    public String processUpdateBooking(@ModelAttribute ("updatedBooking") Booking updatedBooking, @PathVariable ("id") int bookingId){
        Booking currentBooking = bookingService.getBooking(bookingId);
        currentBooking.setNoOfSeats(updatedBooking.getNoOfSeats());
        currentBooking.setBookDate(updatedBooking.getBookDate());
        currentBooking.setRegCode(updatedBooking.getRegCode());
        currentBooking.setTotalAmount(updatedBooking.getTotalAmount());
        currentBooking.setStatus(updatedBooking.isStatus());
        currentBooking.setCustomer(updatedBooking.getCustomer());
        currentBooking.setTrip(updatedBooking.getTrip());
        bookingService.updateBooking(updatedBooking);
        return "redirect:/bookings";
    }

    @RequestMapping("/booking/delete/{id}")
    public String deleteBooking(@PathVariable("id") int bookingId){
        bookingService.deleteBooking(bookingId);
        return "redirect:/bookings";
    }


}

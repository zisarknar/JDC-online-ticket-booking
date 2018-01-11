package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.domains.Booking;
import com.solt.jdc.boot.services.BookingService;
import com.solt.jdc.boot.services.CustomerService;
import com.solt.jdc.boot.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TripService tripService;

    @Autowired
    private MainController mainController;

    @RequestMapping("/bookings")
    public String getAllBooking(Model model) {
        model.addAttribute("bookings", bookingService.getAllBooking());

        Booking book = bookingService.findByNameAndPhone("Thaw Thaw", "0900077");

        System.out.println(book);


        return "admin/booking/index";
    }

    @RequestMapping("/bookings/{id}")
    public String getBooking(Model model, @PathVariable("id") int bookingId) {
        model.addAttribute("booking", bookingService.getBooking(bookingId));
        return "admin/booking/index";
    }

    @RequestMapping(value = "/bookings/add", method = RequestMethod.GET)
    public String addBooking(Model model) {
        Booking booking = new Booking();
        model.addAttribute("allTrip", tripService.getAllTrips());
        model.addAttribute("allCustomer", customerService.getAllCustomers());
        model.addAttribute("newBooking", booking);
        return "admin/booking/addNew";
    }

    @RequestMapping(value = "/bookings/add", method = RequestMethod.POST)
    public String processAddBooking(@ModelAttribute("newBooking") @Valid Booking newBooking, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/booking/addNew";
        }
        mainController.disallowedFieldException(result);
        bookingService.saveBooking(newBooking);
        return "redirect:/admin/bookings";
    }

    @RequestMapping(value = "/booking/update/{id}", method = RequestMethod.GET)
    public String updateBooking(@PathVariable("id") int bookingId, Model model) {
        model.addAttribute("allTrip", tripService.getAllTrips());
        model.addAttribute("allCustomer", customerService.getAllCustomers());
        model.addAttribute("updatedBooking", bookingService.getBooking(bookingId));
        return "admin/booking/update";
    }

    @RequestMapping(value = "/booking/update/{id}", method = RequestMethod.POST)
    public String processUpdateBooking(@ModelAttribute("updatedBooking") @Valid Booking updatedBooking, @PathVariable("id") int bookingId, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/booking/update";
        }
        Booking currentBooking = bookingService.getBooking(bookingId);
        currentBooking.setNoOfSeats(updatedBooking.getNoOfSeats());
        currentBooking.setBookDate(updatedBooking.getBookDate());
        currentBooking.setRegCode(updatedBooking.getRegCode());
        currentBooking.setTotalAmount(updatedBooking.getTotalAmount());
        currentBooking.setStatus(updatedBooking.isStatus());
        currentBooking.setCustomer(updatedBooking.getCustomer());
        currentBooking.setTrip(updatedBooking.getTrip());
        bookingService.updateBooking(updatedBooking);
        return "redirect:/admin/bookings";
    }

    //@RequestMapping("")
    @RequestMapping("/booking/delete/{id}")
    public String deleteBooking(@PathVariable("id") int bookingId) {
        bookingService.deleteBooking(bookingId);
        return "redirect:/admin/bookings";
    }

    @InitBinder
    public void intializeBinder(WebDataBinder binder) {
        binder.setAllowedFields("regCode", "bookDate", "noOfSeats", "totalAmount", "status", "trip", "customer");
    }

}

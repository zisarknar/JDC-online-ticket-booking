package com.solt.jdc.boot.controllers.frontControllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.solt.jdc.boot.domains.Bus;
import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.domains.Trip;

import com.solt.jdc.boot.domains.Booking;
import com.solt.jdc.boot.domains.Bus;
import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.domains.Passenger;
import com.solt.jdc.boot.domains.Trip;
import com.solt.jdc.boot.services.BookingService;
import com.solt.jdc.boot.services.BusService;
import com.solt.jdc.boot.services.CustomerService;
import com.solt.jdc.boot.services.StationService;
import com.solt.jdc.boot.services.TripService;
import com.solt.jdc.boot.utils.IAuthenticationFacade;

@Controller
@RequestMapping("frontendBooking")
public class frontbooking {

    @Autowired
    private TripService tripService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StationService stationService;

    @Autowired
    private BusService busService;

    @Autowired
    private IAuthenticationFacade iAuthenticationFacade;

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/trip/{id}")
    public String getTrip(Model model, @PathVariable("id") int tripFilterId, @ModelAttribute("chosenSeat") int chosenSeat, RedirectAttributes redirect) {
        Trip trip = tripService.getTrip(tripFilterId);

        //Calculating Total
        Bus bus = busService.findById(trip.getBusId());
        double total = trip.getUnitPrice() * chosenSeat;
        model.addAttribute("total", total);

        //Creating new passenger
        Passenger passenger = new Passenger();
        model.addAttribute("passenger", passenger);

        //Getting current Date

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        System.out.println(dateFormat.format(date));


        //sending the booking object to the page
        Integer selectedSeat = chosenSeat;
        model.addAttribute("selectedSeat", selectedSeat);
        Double totalPrice = total;
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("tripId", tripFilterId);
        model.addAttribute("stationName", (stationService.findById(trip.getStationId())).getName());
        model.addAttribute("trip", trip);
        Authentication authentication = iAuthenticationFacade.getAuthentiation();
        String currentLoggedinUser = authentication.getName();
        model.addAttribute("customer", customerService.findByEmail(currentLoggedinUser));
        return "frontend/booking";
    }

    @RequestMapping(value = "/success", method = RequestMethod.POST)
    public String saveBooking(@ModelAttribute("passenger") Passenger passenger, @RequestParam("selectedSeat") Integer chosenSeat, @RequestParam("totalPrice") double total, @RequestParam("tripId") int tripId) {
        Booking currentBooking = new Booking();
        currentBooking.setRegCode(UUID.randomUUID().toString());
        currentBooking.setBookDate(new Date());
        currentBooking.setNoOfSeats(chosenSeat);
        currentBooking.setTotalAmount(total);

        //Finding trip from request parameter tripId
        Trip trip = tripService.getTrip(tripId);
        currentBooking.setTrip(trip);
        currentBooking.setPassenger(passenger);
        bookingService.saveBooking(currentBooking);

        //Retrieving the data just saved to database
        List<Booking> bookingList = bookingService.getAllBooking();
        Booking booking = bookingList.get(bookingList.size() - 1);
        int bookingId = booking.getId();
        System.out.println(bookingId);
        return "redirect:/booking/result/" + bookingId;
    }
}

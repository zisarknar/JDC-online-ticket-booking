package com.solt.jdc.boot.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.solt.jdc.boot.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.solt.jdc.boot.domains.Trip;
import com.solt.jdc.boot.utils.commands.TripFinder;


@Controller
public class MainController {
	
	@Autowired
	private TripService tripService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private CitiesService citiesService;
	
    @RequestMapping("/admin")
    public String getMain(Model model) {
        model.addAttribute("userCount", userService.getCount());
        model.addAttribute("bookingCount", bookingService.getBookingCount());
        model.addAttribute("customerCount", customerService.getCustomerCount());
        return "admin/index";
    }
  
    @GetMapping("/")
    public String getIndex(Model model) {
    	TripFinder tripFinder=new TripFinder();
    	model.addAttribute("allcities",citiesService.getAllCities().stream().map(e->e.getName()).collect(Collectors.toList()));
    	model.addAttribute("tripFinder", tripFinder);
        return "frontend/index";
    }
    
    @RequestMapping(value="/trip/search",method=RequestMethod.POST)
    public String getTrip(@ModelAttribute("tripFinder")TripFinder tripFinder) {
    	String source=tripFinder.getSource();
    	String destination=tripFinder.getDestination();
    	Date depDate=tripFinder.getDepDate();
    	List<Trip> tripList=tripService.findTripByFilter(source, destination,depDate);
    	tripList.stream().forEach(System.out::println);
    	return "redirect:/";
    }
    
    protected void disallowedFieldException(BindingResult result) {
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Unable to bind disallowed fields");
        }
    }

}

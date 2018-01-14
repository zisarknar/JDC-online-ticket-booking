package com.solt.jdc.boot.frontend.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.solt.jdc.boot.domains.Bus;
import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.domains.Trip;
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
	 private IAuthenticationFacade iAuthenticationFacade;
	
	@Autowired
	 private BusService busService;
	@RequestMapping("/trip/{id}")
    public String getTrip(Model model, @PathVariable("id") int tripFilterId,@ModelAttribute("chosenSeat")int chosenSeat) {
		
		Trip trip=tripService.getTrip(tripFilterId);
		
		//Calculating total 
		Bus bus=busService.findById(trip.getBusId());
			
			double total=trip.getUnitPrice()*chosenSeat;
			model.addAttribute("total",total);
		
		
			
		DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		Date date=new Date();
		System.out.println(dateFormat.format(date));
		
		
		
		
		model.addAttribute("stationName",stationService.findById(trip.getStationId()).getName());
			
			Integer selectedSeat=chosenSeat;
		model.addAttribute("selectedSeat",selectedSeat);
			
			Double totalPrice=total;
		model.addAttribute("totalPrice",totalPrice);	
		
        model.addAttribute("trip", trip);
        
        
        Authentication authentication = iAuthenticationFacade.getAuthentiation();        
        String currentLoggedinUser= authentication.getName();
        
        model.addAttribute("customer",customerService.findByusername(currentLoggedinUser));
        
        return "frontend/booking";
        
	} 
        
}

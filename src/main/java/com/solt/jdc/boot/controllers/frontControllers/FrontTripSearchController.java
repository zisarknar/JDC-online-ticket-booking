package com.solt.jdc.boot.controllers.frontControllers;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.solt.jdc.boot.domains.Bus;
import com.solt.jdc.boot.domains.Trip;
import com.solt.jdc.boot.services.BusService;
import com.solt.jdc.boot.services.CitiesService;
import com.solt.jdc.boot.services.TripService;
import com.solt.jdc.boot.utils.TripFinder;


@Controller
public class FrontTripSearchController {
	@Autowired
	private TripService tripService;
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private CitiesService citiesService;

	@RequestMapping("/findBookingTrips")
	public String searchTrips(Model model) {
		TripFinder tripFinder = new TripFinder();
		model.addAttribute("allcities",
				citiesService.getAllCities()
											.stream()//change into  an sequence of cities  ->stream 
													.map(e -> e.getName())  //List တစ်ခုထဲသို့  မြို့နာမည်တွေထည့်တာပါ 
															   .collect(Collectors.toList()));
		model.addAttribute("tripFinder", tripFinder);
		return "/frontend/findTrips";
	}
	
	
	
	@RequestMapping(value = "/busBinding/{id}", method = RequestMethod.POST)
	public String bindBus(@ModelAttribute("bus") Bus bus, @PathVariable("id") int tripId,
															Model model,HttpServletRequest request,
																		RedirectAttributes redirect) {
		Trip trip = tripService.getTrip(tripId);
		Bus currentBus=busService.findById(trip.getBusId());
		
		
		redirect.addAttribute("chosenSeat",bus.getTakenSeats());
		currentBus.setTakenSeats(currentBus.getTakenSeats()+bus.getTakenSeats());
		
		busService.updateBus(currentBus);
		
		
		int id=trip.getId();
		
		return "redirect:/frontendBooking/trip/"+String.valueOf(id);
		
	}
}
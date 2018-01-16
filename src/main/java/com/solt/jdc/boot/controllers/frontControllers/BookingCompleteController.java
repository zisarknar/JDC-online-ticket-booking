package com.solt.jdc.boot.controllers.frontControllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solt.jdc.boot.domains.Booking;
import com.solt.jdc.boot.domains.Bus;
import com.solt.jdc.boot.domains.Station;
import com.solt.jdc.boot.domains.Trip;
import com.solt.jdc.boot.services.BookingService;
import com.solt.jdc.boot.services.BusService;
import com.solt.jdc.boot.services.StationService;
import com.solt.jdc.boot.viewResolvers.PDFGeneratorUtil;

@Controller
public class BookingCompleteController {
	@Autowired
	private PDFGeneratorUtil util;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private StationService stationService;
	
	@RequestMapping(value="/booking/result/{bookingId}")
	public String bookingResult(Model model,@PathVariable("bookingId")int bookingId) {
		Booking booking=bookingService.getBooking(bookingId);
		Trip trip=booking.getTrip();
		model.addAttribute("booking", booking);
		model.addAttribute("trip", trip);
		return "/frontend/result";
	}
	
	@GetMapping("/booking/print/{id}")
	@ResponseBody
	public byte[] printBooking(Model model,@PathVariable("id")int bookingId,HttpServletResponse response) throws Exception {
		
		//Getting the necessary objects for voucher
		
		//Booking
		Booking booking=bookingService.getBooking(bookingId);
		
		//Trip
		Trip trip=booking.getTrip();
		
		//Bus
		int busId=trip.getId();
		Bus bus=busService.findById(busId);
		
		//Station
		int stationId=trip.getStationId();
		Station station=stationService.findById(stationId);
		
		
		//Populating the pdf page with data
		Map<String , String> data=new HashMap<>();
		data.put("source", trip.getSource());
		data.put("destination", trip.getDestination());
		data.put("passengerName", booking.getPassenger().getName());
		data.put("passengerNRC", booking.getPassenger().getRegNo());
		data.put("passengerPhone", booking.getPassenger().getPhone());
		data.put("noOfSeats", String.valueOf(booking.getNoOfSeats()));
		data.put("busType", bus.getBusType().getType());
		data.put("busNumber",bus.getBusNumber());
		data.put("regNo", booking.getRegCode());
		data.put("departureDate", trip.getDepDate().toString());
		data.put("departureTime", trip.getDepTime().toString());
		data.put("stationName", station.getName());
		data.put("datePrinted", new Date().toString());
		data.put("totalPrice", String.valueOf(booking.getTotalAmount()));
		
		//Generating pdf
		
		return util.createPdf("resultpdf",data,response);
	
		
		
	}
}

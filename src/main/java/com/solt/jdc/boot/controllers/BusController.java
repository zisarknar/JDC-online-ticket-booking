package com.solt.jdc.boot.controllers;

import java.security.Provider.Service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solt.jdc.boot.domains.Bus;
import com.solt.jdc.boot.domains.BusType;
import com.solt.jdc.boot.domains.Services;
import com.solt.jdc.boot.repositories.BusRepository;
import com.solt.jdc.boot.services.BusService;
import com.solt.jdc.boot.services.BusTypeService;
import com.solt.jdc.boot.services.ServicesService;

@Controller

public class BusController {
	@Autowired
	private BusService busService;

	@Autowired
	private BusTypeService busTypeService;

	@Autowired
	private BusRepository busRepository;

	@Autowired
	private MainController mainController;

	@Autowired
	private ServicesService servicesService;

	@RequestMapping(value = "bus/add", method = RequestMethod.POST)
	public String addBusPOST(Model model, @ModelAttribute("bus") @Valid Bus newBus, BindingResult result) {
		if (result.hasErrors()) {
			return "admin/bus/addBus";
		}
		mainController.disallowedFieldException(result);
		List<Bus> busList = busService.getAllBus();

		if (busList.size() == 0) {
			newBus.setId(1);
		} else {
			Bus bus = busList.get(busList.size() - 1);
			newBus.setId(bus.getId() + 1);
		}
		busService.createBus(newBus);
		return "redirect:/bus/buses";
	}

	@RequestMapping(value = "bus/buses")
	public String getAllBuses(Model model, HttpServletRequest request) {
		Bus bus = new Bus();
		BusType busType = new BusType();
		Services service = new Services();

		model.addAttribute("bustypes", busTypeService.getAllBusTypes());

		model.addAttribute("manyServices", servicesService.getAllServices());

		model.addAttribute("buses", busService.getAllBus());

		model.addAttribute("bus", bus);

		model.addAttribute("busType", busType);

		model.addAttribute("services", service);

		return "admin/bus/index";
	}

	@RequestMapping(value = "/bus/findByBusType", method = RequestMethod.POST)
	public String findByBusType(@ModelAttribute("busType") BusType busType, Model model) {
		model.addAttribute("buses", busRepository.findByBusType(busType));
		return "forward:/bus/buses";
	}

	@RequestMapping(value = "/bus/delete/{busId}")
	public String deleteBus(@PathVariable("busId") int busId, Model model) {
		busService.deleteBus(busId);
		return "redirect:/bus/buses";
	}

	@ResponseBody
	@RequestMapping(value = "/bus/loadBusTable")
	public List<Bus> loadSomethingTable() {
		return busService.getAllBus();
	}

	@RequestMapping(value = "/bus/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("bus") Bus newBus) {
		Bus currentBus = busService.findById(newBus.getId());
		currentBus.setBusCode(newBus.getBusCode());
		currentBus.setBusCompany(newBus.getBusCompany());
		currentBus.setBusNumber(newBus.getBusNumber());
		currentBus.setMaxSeats(newBus.getMaxSeats());
		currentBus.setTakenSeats(newBus.getTakenSeats());
		currentBus.setBusType(newBus.getBusType());
		busService.updateBus(currentBus);

		return "redirect:/bus/buses";
	}

	@ResponseBody
	@RequestMapping(value = "/bus/loadEntity/{id}", method = RequestMethod.GET)
	public Bus loadEntity(@PathVariable("id") Integer id) {
		return busService.findById(id);
	}

	@InitBinder
	public void intializeBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "busNumber", "busCompany", "busCode", "maxSeats", "takenSeats", "busType");
	}
}

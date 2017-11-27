package com.solt.jdc.boot.controllers;

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

import com.solt.jdc.boot.domains.Address;
import com.solt.jdc.boot.services.AddressService;
import com.solt.jdc.boot.services.CitiesService;

@Controller
@RequestMapping("/address")
public class AddressController  {
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private MainController mainController;
	
	@Autowired
	private CitiesService citiesService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addAddressGET(Model model) {
		Address address=new Address();
		model.addAttribute("address", address);
		model.addAttribute("allcities",citiesService.getAllCities());
		return "admin/address/addAddress";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addAddressPOST(Model model,@ModelAttribute("address")  @Valid Address address,BindingResult result) {
		mainController.disallowedFieldException(result);
		if(result.hasErrors()) {
			return "admin/address/addAddress";
		}
		addressService.addAddress(address);
		return "redirect:/address/addresses";
	}
	
	@RequestMapping("/addresses")
	public String getAllAddresses(Model model) {
		model.addAttribute("addresses", addressService.getAllAddress());
		return "admin/address/index";
	}
	
	@RequestMapping("/delete/{addressId}")
	public String deleteAddress(@PathVariable("addressId")int addressId) {
		addressService.deleteAddress(addressId);
		return "redirect:/address/addresses";
	}
	
	@RequestMapping(value="/update/{addressId}",method=RequestMethod.GET)
	public String updateAddressGET(Model model,@PathVariable("addressId")int addressId) {
		model.addAttribute("address", addressService.findById(addressId));
		model.addAttribute("allcities",citiesService.getAllCities());
		return "admin/address/updateAddressForm";
	}
	
	@RequestMapping(value="/update/{addressId}",method=RequestMethod.POST)
	public String updateAddressPOST(Model model,@ModelAttribute("address") @Valid Address newAddress,@PathVariable("addressId")int addressId,BindingResult result) {
		if(result.hasErrors()) {
			return "admin/address/updateAddressForm";
		}
		
		Address currentAddress=addressService.findById(addressId);
		currentAddress.setAddressName(newAddress.getAddressName());
		currentAddress.setCities(newAddress.getCities());
		
		
		addressService.updateAddress(currentAddress);
		return "redirect:/address/addresses";
	}
	
	@InitBinder
	public void initializeBinder(WebDataBinder binder) {
		binder.setAllowedFields("addressName","cities");
	}
}

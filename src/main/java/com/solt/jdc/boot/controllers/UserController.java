package com.solt.jdc.boot.controllers;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.solt.jdc.boot.domains.User;
import com.solt.jdc.boot.services.Impl.UserService;



@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String findAllUser(Model model) {
		
		model.addAttribute("users",userService.findAll());
		return "user/index";
		
	}
	
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(ModelMap map) {
		map.put("user",new User());
		return "user/add";
		
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String save(@Valid @ModelAttribute("user") User user,BindingResult bindingResult) {
		userService.save(user);
		return "redirect:../user";
	}
	
	@RequestMapping(value="edit/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id") int id,Model model) {
		model.addAttribute("user",userService.find(id));
		return"user/edit";
	}
	
	@RequestMapping(value="edit",method=RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("user") User user,BindingResult bindingResult,Model modle){
		User currentUser=userService.find(user.getId());
		
		currentUser.setUserName(user.getUserName());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setPhone(user.getPhone());
		currentUser.setEmail(user.getEmail());
		currentUser.setRole(user.getRole());
		
		if(!currentUser.isStatus()) {
			currentUser.setStatus(user.isStatus());
		}
		
		if(currentUser.getPassword().isEmpty()) {
			currentUser.setPassword(user.getPassword());
		}
		
		return"redirect:../user";
	}
	
	
	@RequestMapping(value="delete/{id}")
	public String delete(@PathVariable("id") int id) {
		userService.delete(userService.find(id));
		return "redirect:/user";
	}	
}

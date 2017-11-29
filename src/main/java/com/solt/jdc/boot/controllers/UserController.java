package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.domains.User;
import com.solt.jdc.boot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method=RequestMethod.GET, value = "/users")
	public String findAllUser(Model model) {
		model.addAttribute("users",userService.findAll());
		return "admin/user/index";
	}
	
	@RequestMapping(value="/users/add",method=RequestMethod.GET)
	public String add(ModelMap map) {
		map.put("user",new User());
		return "admin/user/userAdd";
	}
	
	@RequestMapping(value="/users/add",method=RequestMethod.POST)
	public String save(@ModelAttribute("user") User user) {
		userService.save(user); 
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value="/user/update/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id") int id,Model model) {
		model.addAttribute("users",userService.findAll());
		model.addAttribute("user",userService.find(id));
		return "admin/user/userEdit";
	}
	
	@RequestMapping(value="/user/update",method=RequestMethod.POST)
	public String edit(@ModelAttribute("user") User user){
		User currentUser=userService.find(user.getId());
		currentUser.setUserName(user.getUserName());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setPhone(user.getPhone());
		currentUser.setEmail(user.getEmail());
		currentUser.setRole(user.getRole());
		currentUser.setStatus(user.isStatus());
		if(currentUser.getPassword().isEmpty()) {
			currentUser.setPassword(user.getPassword());
		}
		userService.update(currentUser);
		return"redirect:/admin/users";
	}
	
	
	@RequestMapping(value="/user/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		userService.delete(userService.find(id));
		return "redirect:/admin/users";
	}	
}
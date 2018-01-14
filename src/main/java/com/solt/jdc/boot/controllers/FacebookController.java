package com.solt.jdc.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/connect")
public class FacebookController extends ConnectController {

	public FacebookController(ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	@Override
	protected String connectedView(String providerId) {
		return "redirect:/";
	}

	@Autowired
	private Facebook facebook;

	@RequestMapping(value = "/facebook", method = RequestMethod.GET)
	public String helloFacebook(Model model) {
		if (!facebook.isAuthorized()) {
			return "redirect:/loign";
		}
		String[] fields = { "id", "name", "birthday", "email", "location", "hometown", "gender", "first_name",
				"last_name" };
		model.addAttribute("facebookProfile", facebook.fetchObject("me", User.class, fields));
		return "/admin/index";
	}

}

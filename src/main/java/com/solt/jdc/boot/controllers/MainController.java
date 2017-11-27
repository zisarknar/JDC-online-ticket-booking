package com.solt.jdc.boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    @RequestMapping("/master")
    public String getMaster(){
        return "master";
    }

    @RequestMapping("/login")
    public String getLogin(){
        return "admin/login";
    }

    @RequestMapping("/")
    public String getIndex() {
        return "admin/index";
    }
    
    protected void disallowedFieldException(BindingResult result) {
		String[] suppressedFields=result.getSuppressedFields();
    	if(suppressedFields.length>0) {
    		throw new RuntimeException("Unable to bind disallowed fields");
    	}
	}

}

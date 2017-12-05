package com.solt.jdc.boot.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/admin")
    public String getMain(){
        return "admin/index";
    }

    @GetMapping("/")
    public String getIndex() {
        return "front-master";
    }
    
    protected void disallowedFieldException(BindingResult result) {
		String[] suppressedFields=result.getSuppressedFields();
    	if(suppressedFields.length>0) {
    		throw new RuntimeException("Unable to bind disallowed fields");
    	}
	}

}

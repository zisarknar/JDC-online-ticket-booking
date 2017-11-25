package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.utils.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @RequestMapping("/admin/")
    public String getMain(){
        return "admin/index";
    }

    @RequestMapping("/")
    public String getFront(){
        return "index";
    }
}

package com.solt.jdc.boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/admin/")
    public String getMain(){
        return "admin/index";
    }

}

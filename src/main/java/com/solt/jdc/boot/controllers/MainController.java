package com.solt.jdc.boot.controllers;

import org.springframework.stereotype.Controller;
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


    protected void test(String msg) {
        System.out.println(msg);
    }
}

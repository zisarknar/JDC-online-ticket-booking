package com.solt.jdc.boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ErrorController {

    @GetMapping("/error/403")
    public String getError403() {
        return "errors/403";
    }
}

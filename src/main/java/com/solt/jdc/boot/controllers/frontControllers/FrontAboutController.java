package com.solt.jdc.boot.controllers.frontControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontAboutController {

    @RequestMapping("/about")
    public String getAboutPage() {
        return "frontend/about";
    }
}

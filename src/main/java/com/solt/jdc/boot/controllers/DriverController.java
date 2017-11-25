package com.solt.jdc.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.solt.jdc.boot.domains.Driver;
import com.solt.jdc.boot.services.DriverService;


@Controller
@RequestMapping("admin")
public class DriverController {

    @Autowired
    private DriverService driverService;


    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public String findAlldriver(Model model) {
        model.addAttribute("drivers", driverService.findAll());
        return "admin/driver/index";
    }

    @RequestMapping(value = "/drivers/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        map.put("driver", new Driver());
        return "admin/driver/driverAdd";

    }

    @RequestMapping(value = "/drivers/add", method = RequestMethod.POST)
    public String save(@ModelAttribute("driver") Driver driver) {
        driverService.save(driver);
        return "redirect:/admin/driver";
    }

    @RequestMapping(value = "/driver/update/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("drivers", driverService.findAll());
        model.addAttribute("driver", driverService.find(id));
        return "admin/driver/driverEdit";
    }

    @RequestMapping(value = "/driver/update/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("driver") Driver driver) {
        Driver currentDriver = driverService.find(driver.getId());
        currentDriver.setName(driver.getName());
        currentDriver.setPhone(driver.getPhone());
        currentDriver.setEmail(driver.getEmail());
        currentDriver.setDriverRole(driver.getDriverRole());
        currentDriver.setCode(driver.getCode());
        currentDriver.setStatus(driver.isStatus());
        driverService.update(currentDriver);
        return "redirect:/admin/drivers";
    }


    @RequestMapping(value = "/driver/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        driverService.delete(driverService.find(id));
        return "redirect:/admin/drivers";
    }
}


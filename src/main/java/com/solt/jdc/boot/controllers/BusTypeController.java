package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.domains.BusType;
import com.solt.jdc.boot.services.BusTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BusTypeController {

    @Autowired
    private BusTypeService busTypeService;

    @Autowired
    private MainController mainController;

    @RequestMapping(value = "/bustypes/add", method = RequestMethod.POST)
    public String addBusTypePost(@ModelAttribute("busType") @Valid BusType busType, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/bustypes/addBusType";
        }
        List<BusType> busTypeList = busTypeService.getAllBusTypes();
        if (busTypeList.size() == 0) {
            busType.setId(1);
        } else {
            BusType busTypeForId = busTypeList.get(busTypeList.size() - 1);
            busType.setId(busTypeForId.getId() + 1);
        }
        mainController.disallowedFieldException(result);
        busTypeService.addBusType(busType);
        return "redirect:/admin/buses";
    }

  

    @RequestMapping(value = "/bustype/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("busType") @Valid BusType newBusType) {
        BusType currentBusType = busTypeService.findById(newBusType.getId());
        currentBusType.setType(newBusType.getType());
        busTypeService.updateBusType(currentBusType);
        return "redirect:/admin/buses";
    }

    @ResponseBody
    @RequestMapping(value = "/bustype/loadEntity/{id}", method = RequestMethod.GET)
    public BusType loadEntity(@PathVariable("id") Integer id) {
        return busTypeService.findById(id);
    }

    @RequestMapping("/bustype/delete/{id}")
    public String deleteBusType(@PathVariable("id") int id) {
        busTypeService.deleteBusType(busTypeService.findById(id));
        return "redirect:/admin/buses";
    }

    @InitBinder
    public void initializeBinder(WebDataBinder binder) {
        binder.setAllowedFields("id", "type");
    }


}

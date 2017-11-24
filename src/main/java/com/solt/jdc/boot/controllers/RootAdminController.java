package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.domains.RootAdmin;
import com.solt.jdc.boot.services.RootAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootAdminController {

    @Autowired
    private RootAdminService rootAdminService;



    @RequestMapping("/roots")
    public String getAllRootAdmin(Model model){
        model.addAttribute("roots", rootAdminService.getAllRootAdmin());
        return "admin/rootAdmin/index";
    }

    @RequestMapping("/root/{id}")
    public String getRootAdmin(@PathVariable ("id") int rootAdminId, Model model) {
        model.addAttribute("root", rootAdminService.getRootAdmin(rootAdminId));
        return "admin/rootAdmin/index";
    }

    @RequestMapping(value = "/roots/add", method = RequestMethod.GET)
    public String addRootAdmin(Model model){
        RootAdmin rootAdmin = new RootAdmin();
        model.addAttribute("newRootAdmin", rootAdmin);
        return "admin/rootAdmin/addNew";
    }

    @RequestMapping(value = "/roots/add", method = RequestMethod.POST)
    public String processAddRootAdmin(@ModelAttribute("newRootAdmin") RootAdmin rootAdmin, Model model){
        rootAdminService.saveRootAdmin(rootAdmin);
        return "redirect:/roots";
    }

    @RequestMapping(value = "/root/update/{id}", method = RequestMethod.GET)
    public String updateRootAdmin(@PathVariable ("id") int rootAdminId, Model model){
        model.addAttribute("updatedRootAdmin", rootAdminService.getRootAdmin(rootAdminId));
        return "admin/rootAdmin/update";
    }

    @RequestMapping(value = "/root/update/{id}", method = RequestMethod.POST)
    public String processUpdateRootAdmin(@ModelAttribute("updatedRootAdmin") RootAdmin updatedAdmin, @PathVariable ("id") int updateAdminId){
        RootAdmin currentRootAdmin = rootAdminService.getRootAdmin(updateAdminId);
        currentRootAdmin.setRootName(updatedAdmin.getRootName());
        currentRootAdmin.setRootPassword(updatedAdmin.getRootPassword());
        rootAdminService.updateRootAdmin(currentRootAdmin);
        return "redirect:/roots";
    }

    @RequestMapping("/root/delete/{id}")
    public String removeRootAdmin(@PathVariable ("id") int rootAdminId){
        rootAdminService.deleteRootAdmin(rootAdminId);
        return "redirect:/roots";
    }


}

package com.solt.jdc.boot.controllers;

import com.solt.jdc.boot.services.BookingService;
import com.solt.jdc.boot.utils.commands.BookFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/admin/report")
public class ReportController {

    private BookingService bookingService;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping("/booking")
    public String getBookingReport(Model model) {
        BookFinder bookFinder = new BookFinder();
        model.addAttribute("bookFinder", bookFinder);
        return "admin/reports/index";
    }

    @RequestMapping(value = "/booking/result", method = RequestMethod.POST)
    public String processGetBookingReport(@ModelAttribute("bookFinder") @Valid BookFinder bookFinder, Model model) {
        Date fromDate = bookFinder.getFromDate();
        Date toDate = bookFinder.getToDate();
        System.out.println(toDate);
        model.addAttribute("bookingsByDate", bookingService.getBookingReport(fromDate, toDate));
        return "redirect:/admin/report/booking";
    }
}

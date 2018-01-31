package com.solt.jdc.boot.controllers.passwordforget.controller;


import com.solt.jdc.boot.domains.Customer;
import com.solt.jdc.boot.domains.Mail;
import com.solt.jdc.boot.domains.PasswordResetToken;
import com.solt.jdc.boot.controllers.passwordforget.DTOs.PasswordForgotDto;
import com.solt.jdc.boot.repositories.PasswordResetTokenRepository;
import com.solt.jdc.boot.services.CustomerService;
import com.solt.jdc.boot.services.Impl.EmailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private EmailServiceImpl emailService;

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "frontend/forgot-password";
    }

    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
                                            BindingResult result,
                                            HttpServletRequest request) {
        if (result.hasErrors()) {
            return "frontend/forgot-password";
        }

        Customer customer = customerService.findByEmail(form.getEmail());
        if (customer == null) {
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "frontend/forgot-password";
        }
        //if (customer != null)
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setCustomer(customer);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("admin@jdconlineticketbooking.com");
        mail.setTo(customer.getEmail());
        mail.setSubject("Password reset request");
        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("customer", customer);
        model.put("signature", "https://mail.google.com");//https://mail.google.com/mail/u/0/#inbox
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);
        return "redirect:/forgot-password?success";

    }

}

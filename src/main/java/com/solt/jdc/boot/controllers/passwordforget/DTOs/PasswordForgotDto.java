package com.solt.jdc.boot.controllers.passwordforget.DTOs;

import com.solt.jdc.boot.validators.customAnnotations.ValidEmail;
import org.hibernate.validator.constraints.NotEmpty;

public class PasswordForgotDto {

    @ValidEmail
    @NotEmpty
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

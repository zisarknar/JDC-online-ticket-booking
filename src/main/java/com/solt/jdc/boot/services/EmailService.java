package com.solt.jdc.boot.services;

import com.solt.jdc.boot.domains.Mail;

public interface EmailService {
    public void sendEmail(Mail mail);
}

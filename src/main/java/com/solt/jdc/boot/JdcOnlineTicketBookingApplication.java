package com.solt.jdc.boot;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class JdcOnlineTicketBookingApplication {

	@Bean
	public LocaleResolver resolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		return resolver;
	}

	public static void main(String[] args) {
		SpringApplication.run(JdcOnlineTicketBookingApplication.class, args);
	}
}

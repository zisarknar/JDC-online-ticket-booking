package com.solt.jdc.boot;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.solt.jdc.boot.services.CitiesService;
@EntityScan(basePackageClasses= {JdcOnlineTicketBookingApplication.class,Jsr310JpaConverters.class})
@SpringBootApplication
public class JdcOnlineTicketBookingApplication {

    @Autowired
    CitiesService citiesService;

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

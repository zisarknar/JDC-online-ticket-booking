package com.solt.jdc.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.Set;

import com.solt.jdc.boot.services.CitiesService;

@EntityScan(basePackageClasses = { JdcOnlineTicketBookingApplication.class, Jsr310JpaConverters.class })

@SpringBootApplication
@EnableAutoConfiguration
@EnableOAuth2Sso
public class JdcOnlineTicketBookingApplication {

	@Bean
	WebMvcConfigurerAdapter webMvcConfigurerAdapter() {

		return new WebMvcConfigurerAdapter() {

			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addRedirectViewController("/", "/login");
			}

		};
	}

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
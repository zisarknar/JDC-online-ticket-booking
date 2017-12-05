package com.solt.jdc.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.solt.jdc.boot.handlers.SuccessHandler;

@Configuration
public class BeanConfig {

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new SuccessHandler();
	}
}


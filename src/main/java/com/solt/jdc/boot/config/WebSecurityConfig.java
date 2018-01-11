package com.solt.jdc.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    @Qualifier("customer_details_service")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").authenticated()
                .antMatchers("/admin/error/**").authenticated()
                .antMatchers("/admin/roots/**").hasRole("ROOT")
                .antMatchers("/customers/**").hasRole("ROOT")
                .antMatchers("/customerdetails/**").authenticated()
                .antMatchers("/admin/bookings/**").hasAnyRole("ROOT", "MANAGER", "STAFF")
                .antMatchers("/admin/buses/**").hasAnyRole("ROOT", "MANAGER")
                .antMatchers("/admin/addresses/**").hasAnyRole("ROOT", "MANAGER")
                .antMatchers("/admin/bustypes/**").hasAnyRole("ROOT", "MANAGER")
                .antMatchers("/admin/cities/**").hasAnyRole("ROOT", "MANAGER")
                .antMatchers("/admin/drivers/**").hasAnyRole("ROOT", "MANAGER")
                .antMatchers("/admin/services/**").hasAnyRole("ROOT", "MANAGER")
                .antMatchers("/admin/stations/**").hasAnyRole("ROOT", "MANAGER")
                .antMatchers("/admin/trips/**").hasAnyRole("ROOT", "MANAGER")
                .antMatchers("/admin/users/**").hasAnyRole("ROOT", "MANAGER")
                .and()
                .formLogin()
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin/")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("staff").password("password").roles("STAFF")
                .and()
                .withUser("manager").password("password").roles("MANAGER")
                .and()
                .withUser("sargon").password("sargon").roles("ROOT");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}

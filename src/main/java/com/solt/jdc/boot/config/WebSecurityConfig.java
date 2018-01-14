package com.solt.jdc.boot.config;

import com.solt.jdc.boot.handlers.AccessDeniedHandler;
import com.solt.jdc.boot.services.CustomerService;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

import org.springframework.security.web.access.AccessDeniedHandler;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    private OAuth2ClientContext oauth2ClientContext;
	private AuthorizationCodeResourceDetails authorizationCodeResourceDetails;
	private ResourceServerProperties resourceServerProperties;

	@Autowired
	public void setOauth2ClientContext(OAuth2ClientContext oauth2ClientContext) {
		this.oauth2ClientContext = oauth2ClientContext;
	}

	@Autowired
	public void setAuthorizationCodeResourceDetails(AuthorizationCodeResourceDetails authorizationCodeResourceDetails) {
		this.authorizationCodeResourceDetails = authorizationCodeResourceDetails;
	}

	@Autowired
	public void setResourceServerProperties(ResourceServerProperties resourceServerProperties) {
		this.resourceServerProperties = resourceServerProperties;
	}

	/*
	 * This method is for overriding the default AuthenticationManagerBuilder. We
	 * can specify how the user details are kept in the application. It may be in a
	 * database, LDAP or in memory.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}


    @Autowired
    @Qualifier("customer_details_service")
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomerService customerService;
    
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
                .antMatchers("/forgot-password/**",
                			"/reset-password/**").permitAll()
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
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .addFilterAt(filter(),BasicAuthenticationFilter.class)
                .csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
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
    
    private OAuth2ClientAuthenticationProcessingFilter filter() {
		// Creating the filter for "/google/login" url
		OAuth2ClientAuthenticationProcessingFilter oAuth2Filter = new OAuth2ClientAuthenticationProcessingFilter(
				"/google/login");

		// Creating the rest template for getting connected with OAuth service.
		// The configuration parameters will inject while creating the bean.
		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(authorizationCodeResourceDetails,
				oauth2ClientContext);
		oAuth2Filter.setRestTemplate(oAuth2RestTemplate);


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider auth=new  DaoAuthenticationProvider();
    	
    	auth.setUserDetailsService((UserDetailsService) customerService);
    	auth.setPasswordEncoder(passwordEncoder());
    	
    	return auth;
    	
    	
    }

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}*/

		// Setting the token service. It will help for getting the token and
		// user details from the OAuth Service.
		oAuth2Filter.setTokenServices(new UserInfoTokenServices(resourceServerProperties.getUserInfoUri(),
				resourceServerProperties.getClientId()));


		return oAuth2Filter;
	}

}

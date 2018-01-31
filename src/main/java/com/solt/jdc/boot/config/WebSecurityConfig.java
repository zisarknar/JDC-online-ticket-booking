package com.solt.jdc.boot.config;

import com.solt.jdc.boot.repositories.CustomerRepository;
import com.solt.jdc.boot.services.CustomerService;
import com.solt.jdc.boot.utils.FacebookConnectionSignup;
import com.solt.jdc.boot.utils.FacebookSignInAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.api.Facebook;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = CustomerRepository.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired

    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private Facebook facebook;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;

    @Autowired
    private CustomerService customerService;


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

    @Autowired
    @Qualifier("customer_details_service")
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasAnyRole("ROOT", "ADMIN", "STAFF")
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
                .formLogin()
                .loginPage("/frontend/login")

                .permitAll()
                .and()
                .logout()
                .clearAuthentication(true)
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .addFilterAt(filter(), BasicAuthenticationFilter.class)
                .csrf()

                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                .disable();
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
    public ProviderSignInController providerSignInController() {
        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
                .setConnectionSignUp(facebookConnectionSignup);

        return new ProviderSignInController(
                connectionFactoryLocator,
                usersConnectionRepository,
                new FacebookSignInAdapter());
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
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(authorizationCodeResourceDetails, oauth2ClientContext);
        oAuth2Filter.setRestTemplate(oAuth2RestTemplate);
        // Setting the token service. It will help for getting the token and
        // user details from the OAuth Service.
        oAuth2Filter.setTokenServices(new UserInfoTokenServices(resourceServerProperties.getUserInfoUri(),
                resourceServerProperties.getClientId()));
        return oAuth2Filter;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService((UserDetailsService) customerService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

}


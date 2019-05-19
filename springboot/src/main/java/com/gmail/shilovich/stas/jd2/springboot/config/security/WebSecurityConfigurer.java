package com.gmail.shilovich.stas.jd2.springboot.config.security;

import com.gmail.shilovich.stas.jd2.springboot.config.handler.AppUrlAuthenticationHandler;
import com.gmail.shilovich.stas.jd2.springboot.config.handler.WebAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/private/users", "/private/users/new", "/private/reviews")
                .hasAuthority("ADMINISTRATOR")
                .antMatchers("/", "/403", "/public/login")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(webAccessDeniedHandler())
                .and()
                .csrf()
                .disable();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AppUrlAuthenticationHandler();
    }

    @Bean
    public AccessDeniedHandler webAccessDeniedHandler() {
        return new WebAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

}

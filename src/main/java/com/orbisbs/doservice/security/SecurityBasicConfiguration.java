package com.orbisbs.doservice.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@AllArgsConstructor
@Order(-1)
@Configuration
public class SecurityBasicConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {



        http.csrf().disable()
                .requestMatchers().antMatchers("/cars/**")
                .and()
                .authorizeRequests().anyRequest().hasRole("ADMIN")
                .and().httpBasic();


    }

}

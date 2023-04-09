package com.platzi.market.web.security;

import com.tienda.deunomarket.domain.service.MarketUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MarketUserDetailService marketUserDetailService;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)  throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(marketUserDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }
}
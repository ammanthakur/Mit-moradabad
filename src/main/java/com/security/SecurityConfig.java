package com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeHttpRequests(authorize -> {
                authorize
                    .requestMatchers("/req/signup", "/css/**", "/js/**").permitAll() // Allow these paths
                    .anyRequest().authenticated(); // All other requests require authentication
            })
            .formLogin(form -> {
                form
                    .loginPage("/login") // Custom login page
                    .permitAll(); // Allow everyone to access the login page
            })
            .logout(logout -> {
                logout.permitAll(); // Allow logout for everyone
            });
    
        return httpSecurity.build();
    }
    
}

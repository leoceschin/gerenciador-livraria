package com.ceschin.library.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurity {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .and()
            .authorizeHttpRequests()
            .antMatchers(HttpMethod.GET, "/api/books/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/books/update-inventory-quantity/**").hasRole("ROLE_ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ROLE_ADMIN")
            .antMatchers("/api/loans/add-book").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
            .antMatchers("/api/loans/close-loan/**").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
            .antMatchers("/api/users/new-user").hasAnyRole("ROLE_ADMIN")
            .anyRequest().authenticated()
            .and()
            .csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

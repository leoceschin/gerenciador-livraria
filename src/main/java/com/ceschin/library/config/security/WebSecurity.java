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
            .antMatchers("/api/books/collection").hasAnyRole("USER", "ADMIN")
            .antMatchers("/api/books/{id}").hasAnyRole("USER", "ADMIN")
            .antMatchers("/api/books/new-book").hasRole("ADMIN")
            .antMatchers("/api/books/delete/{id}").hasRole("ADMIN")
            .antMatchers("/api/loans/add-book-loan").hasAnyRole("USER", "ADMIN")
            .antMatchers("/api/loans/delete-book-loan").hasAnyRole("USER", "ADMIN")
            .antMatchers("/api/loans/close-loan/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/api/users/new-user").hasAnyRole("ADMIN")
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

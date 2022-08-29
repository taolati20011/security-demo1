package com.example.securitydemo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = false, jsr250Enabled = true)
public class Config {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(20);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .cors()
//                .and()
//                .csrf()
//                .disable()
                .authorizeRequests()
                .antMatchers("/home").permitAll()
//                .antMatchers("/add").hasRole("ADMIN")
//                .antMatchers("/add-role").permitAll()
//                .antMatchers("/add").permitAll()
//                .antMatchers("/search-role/**").permitAll()
//                .antMatchers("/get-by-id/**").permitAll()
//                .antMatchers("/delete/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        return http.build();
    }
}

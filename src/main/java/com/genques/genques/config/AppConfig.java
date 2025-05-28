package com.genques.genques.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

public class AppConfig {

    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails userDetails=User.builder().username("vinit").password(passwordEncoder().encode("amit")).roles("ADMIN").build();
    //     return new InMemoryUserDetailsManager(userDetails);
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder(){
    //     return new BCryptPasswordEncoder();
    // }
    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // ✅ CSRF explicitly disabled
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/home/uploads").authenticated()
                .requestMatchers(HttpMethod.GET, "/home/user").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults()); // ✅ Enables Basic Auth

        return http.build();
    }


}

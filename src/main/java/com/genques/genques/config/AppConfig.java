package com.genques.genques.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;


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
//     @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable()) // ✅ CSRF explicitly disabled
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers(HttpMethod.POST, "/home/uploads").authenticated()
//                 .requestMatchers(HttpMethod.GET, "/home/user").authenticated()
//                 .anyRequest().permitAll()
//             )
//             .httpBasic(Customizer.withDefaults()); // ✅ Enables Basic Auth

//         return http.build();
//     }


}

package com.Patient_system.Patient._Aplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
//@EnableWebSecurity

public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/user/register").permitAll() // allow unauthenticated access here
//                        .anyRequest().authenticated()
//                )
//                .csrf(AbstractHttpConfigurer::disable) // disables CSRF in functional style
//                .httpBasic(Customizer.withDefaults()); // still works with default config
//
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/register", "/login").permitAll()
//                        .anyRequest().authenticated()
//                );
//        return http.build();
//    }

}

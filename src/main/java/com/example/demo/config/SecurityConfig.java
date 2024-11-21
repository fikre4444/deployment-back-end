package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration
        .setAllowedOrigins(List.of("*")); // Allow
                                          // this
    // specific origin
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow these HTTP methods
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); // Allow these headers
    configuration.setAllowCredentials(true); // Allow cookies or credentials

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); // Apply CORS to all endpoints
    return source;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http = http.httpBasic(customizer -> customizer.disable())
        // .cors(customizer ->
        // customizer.configurationSource(corsConfigurationSource()))
        // .cors(customizer -> customizer.disable())
        // .cors().disable()
        .cors(customizer -> customizer.configurationSource(request -> {
          CorsConfiguration configuration = new CorsConfiguration();
          configuration.setAllowedOrigins(List.of("*")); // Allow all origins
          configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
          configuration.setAllowedHeaders(List.of("*"));
          return configuration;
        }))
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(customizer -> customizer
            .requestMatchers("/api/**", "*").permitAll())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }

}

package com.lidn.kdrama_app.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.lidn.kdrama_app.service.userServices.UserAuthServices;

import jakarta.servlet.http.HttpServletResponse;

/* 
    Overview: Initial handling of requests to the server
*/

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private UserAuthServices customOAuth2Service;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
            .cors(Customizer.withDefaults())
            // disable CORS error for development, REPLACE IN PROD
            .csrf(csrf -> csrf.disable())

            // make stateless so it's RESTful 
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Prevent session from being created
            .requestCache(cache -> cache.disable())

            // Allow http requests that match paths
            .authorizeHttpRequests((registry) -> {
                registry.requestMatchers("/", "/error", "/login/**", "/oauth2/**").permitAll();
                registry.requestMatchers("/api/public/").permitAll();
                registry.requestMatchers("/actuator/").permitAll();
                registry.anyRequest().authenticated();
            })

            // Handle all exceptions
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"Unauthorized access - " + authException.getMessage() + "\"}");
                })
            )
            
            // oauth2 login component
            // Only activates in when /oauth2 endpoint hit
            .oauth2Login(oauth2 -> {
                oauth2.authorizationEndpoint(authorization -> 
                authorization.authorizationRequestRepository(cookieAuthorizationRequestRepository));

                oauth2.userInfoEndpoint(userInfo -> 
                    userInfo.oidcUserService(customOAuth2Service)
                );
                oauth2.successHandler(authenticationSuccessHandler);
            })

            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            
            .build();
    }

    // Allow frontend to send requests to backend
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

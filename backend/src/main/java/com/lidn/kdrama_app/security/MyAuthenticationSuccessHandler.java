package com.lidn.kdrama_app.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.lidn.kdrama_app.entity.User;
import com.lidn.kdrama_app.enums.Role;
import com.lidn.kdrama_app.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Define where to send JWT
    @Value("${redirect_url}")
    private String targetUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 1. Unpack our custom principal
        CustomOAuth2User customPrincipal = (CustomOAuth2User) authentication.getPrincipal();
        
        // 2. Get the entity directly from memory (No DB Call!)
        User user = customPrincipal.getUserEntity();

        // Generate JWT
        String token = jwtTokenProvider.generateToken(user);

        // Generate Cookie to store JWT
        Cookie jwtCookie = new Cookie("jwt_token", token);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(request.isSecure());
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(86400);

        response.addCookie(jwtCookie);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}

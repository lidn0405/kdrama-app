package com.lidn.kdrama_app.security;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.lidn.kdrama_app.entity.User;
import com.lidn.kdrama_app.enums.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private int jwtExpirationInMs;
    
    private Key key;

    // This method is called after the properties are set
    @jakarta.annotation.PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(User userPrincipal) {
        List<String> roles = userPrincipal.getAuthorities().stream()
            .map(authority -> authority.getAuthority())
            .collect(Collectors.toList());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getEmail())
                .claim("id", userPrincipal.getId())
                .claim("username", userPrincipal.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public User getUserFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        // 1. Create an empty instance of YOUR Entity
        com.lidn.kdrama_app.entity.User user = new com.lidn.kdrama_app.entity.User();

        // 2. Manually stuff the data back in
        user.setEmail(claims.getSubject());
        user.setId(claims.get("id", Long.class));
        user.setPictureUrl(claims.get("pictureUrl", String.class));
        user.setUsername(claims.get("username", String.class));

        // 3. Handle Roles (using the logic we fixed earlier)
        Object roleObject = claims.get("roles");
        if (!(roleObject instanceof List<?>)) {
            return null;
        }
        List<?> roleNames = (List<?>) roleObject;

        if (roleNames != null && !roleNames.isEmpty()) {
            String roleString = (String) roleNames.get(0);
            user.setRole(Role.valueOf(roleString));
        }

        return user;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (SecurityException | MalformedJwtException ex) {
            logger.error("Invalid JWT signature");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    public List<GrantedAuthority> getAuthoritiesFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();

        Object rolesObject = claims.get("roles");
        if (!(rolesObject instanceof List<?>)) {
            return List.of();
        }

        List<?> roles = (List<?>) rolesObject;

        return roles.stream()
            .filter(role -> role instanceof String)
            .map(role -> new SimpleGrantedAuthority((String) role))
            .collect(Collectors.toList());
    }
}

package com.lidn.kdrama_app.security;

import com.lidn.kdrama_app.entity.User;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class CustomOAuth2User extends DefaultOidcUser {

    private final User userEntity;

    public CustomOAuth2User(OidcUser oidcUser, User userEntity) {
        // We pass the OIDC data up to the parent class so Spring functions normally
        super(oidcUser.getAuthorities(), oidcUser.getIdToken(), oidcUser.getUserInfo());
        this.userEntity = userEntity;
    }

    public User getUserEntity() {
        return userEntity;
    }
}
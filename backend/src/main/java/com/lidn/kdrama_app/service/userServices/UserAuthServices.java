package com.lidn.kdrama_app.service.userServices;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lidn.kdrama_app.entity.User;
import com.lidn.kdrama_app.repository.UserRepository;
import com.lidn.kdrama_app.security.CustomOAuth2User;

@Service
public class UserAuthServices extends OidcUserService implements UserDetailsService {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserAuthServices(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. Delegate to the default OidcUserService to load the user from Google
        OidcUser oidcUser = super.loadUser(userRequest);

        // 2. Extract user details
        String email = oidcUser.getAttribute("email");
        String name = oidcUser.getAttribute("name");
        String pictureUrl = oidcUser.getAttribute("picture");
        String googleId = oidcUser.getAttribute("sub");

        // 3. Save or Update the user in the database
        // This method flushes changes to the DB so findByEmail will work immediately after
        userService.processUserFromAuth(googleId, name, email, pictureUrl);

        // 4. Retrieve the User entity inside this transaction
        User userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new OAuth2AuthenticationException("Transaction failed: User not found after save"));

        // 5. Wrap the OidcUser and our User entity into CustomOAuth2User
        return new CustomOAuth2User(oidcUser, userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Entity not found with email: " + username));
    }
}
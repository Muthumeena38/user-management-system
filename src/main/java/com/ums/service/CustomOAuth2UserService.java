package com.ums.service;

import com.ums.model.User;
import com.ums.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String googleSub = oauth2User.getAttribute("sub");
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String picture = oauth2User.getAttribute("picture");

        User user = userRepository.findByGoogleSub(googleSub)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setGoogleSub(googleSub);
                    newUser.setEmail(email);
                    newUser.setRole(User.Role.USER); // Default role
                    return newUser;
                });

        user.setName(name);
        user.setPhotoUrl(picture);
        userRepository.save(user);

        return oauth2User;
    }
}

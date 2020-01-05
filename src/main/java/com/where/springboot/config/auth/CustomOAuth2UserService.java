package com.where.springboot.config.auth;

import com.where.springboot.config.auth.dto.OAuthAttributes;
import com.where.springboot.config.auth.dto.SessionUser;
import com.where.springboot.domain.user.User;
import com.where.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 현재 로그인 진행중인 서비스를 구분하는 코드, 구글 or 네이버 or 카카오 등 여러개의 OAuth 서비스를 이용하면 어떤 OAuth인증인지 구분하기위해 사용한다.
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); // OAuth2 로그인 진행시 키가 되는 필드값, Primary Key와 같은 의미이다. 나중에 네이버 로그인과 구글 로그인을 동시 지원할 때 사용된다.

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes()); // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user)); // SessionUser는 세션에 사용자 정보를 저장하기위한 Dto 클래스 이다.
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}

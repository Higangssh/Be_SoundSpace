package com.the.soundspace.auth.security.handler;

import com.the.soundspace.auth.oauth2.OauthUserInfo;
import com.the.soundspace.user.model.entities.UserEntity;
import com.the.soundspace.user.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    private static final String[] nouns = {"꽃", "나무", "바다", "하늘", "별", "빛", "음악", "사랑", "행복", "꿈", "모래", "강", "바람", "햇살", "눈", "비", "숲", "감성", "평화", "세상"};
    private static final String[] adjectives = {"화려한", "창의적인", "열정적인", "자유로운", "활기찬", "격렬한", "쾌활한", "우아한", "현명한", "우아한", "정신없는", "신비로운", "풍부한", "안정된", "다채로운", "유쾌한", "진실한", "영리한", "열정적인", "차분한"};
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication instanceof OAuth2AuthenticationToken oauthToken) {
            processOAuth2Authentication(oauthToken);
        }



        response.sendRedirect("https://naver.com");

    }

    private void processOAuth2Authentication(OAuth2AuthenticationToken oauthToken) {
        String registrationId = oauthToken.getAuthorizedClientRegistrationId();
        OAuth2User user = oauthToken.getPrincipal();
        Map<String, Object> attributes = user.getAttributes();

        OauthUserInfo userInfo = OauthUserInfo.userInfo(registrationId, attributes);
        UserEntity newUser = userService.createUser(userInfo.getEmail());
    }

}

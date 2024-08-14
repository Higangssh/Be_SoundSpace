package com.the.soundspace.auth.security.handler;

import com.the.soundspace.auth.oauth2.GoogleUser;
import com.the.soundspace.auth.oauth2.KaKaoUser;
import com.the.soundspace.auth.oauth2.OauthUserInfo;
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


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication instanceof OAuth2AuthenticationToken oauthToken) {
            String registrationId = oauthToken.getAuthorizedClientRegistrationId();
            OAuth2User user = oauthToken.getPrincipal();
            Map<String, Object> attributes = user.getAttributes();

            // 팩토리를 사용해 OAuth2UserInfo 객체 생성
            OauthUserInfo userInfo=getOAuth2UserInfo(registrationId, attributes);
            String email =  userInfo.getEmail();
            String name =  userInfo.getName();

            System.out.println("email: "+email);
            System.out.println("name: "+name);


        }


        response.sendRedirect("https://naver.com");

    }
    private static OauthUserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase("google")) {
            return new GoogleUser(attributes);
        } else if (registrationId.equalsIgnoreCase("kakao")) {
            return new KaKaoUser(attributes);
        } else {
            throw new IllegalArgumentException("Login with " + registrationId + " is not supported yet.");
        }
    }
}

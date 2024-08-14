package com.the.soundspace.auth.oauth2;

import java.util.Map;

public class KaKaoUser implements OauthUserInfo{

    private Map<String, Object> attributes;

    public KaKaoUser(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getName() {
        Map<String, Object> kakaoProfile = (Map<String, Object>) ((Map<String, Object>) attributes.get("kakao_account")).get("profile");
        return (String) kakaoProfile.get("nickname");
    }
}

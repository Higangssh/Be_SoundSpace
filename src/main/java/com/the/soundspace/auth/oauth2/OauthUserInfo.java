package com.the.soundspace.auth.oauth2;

import java.util.Map;

public interface OauthUserInfo {
    String getEmail();

    String getName();

    static OauthUserInfo userInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase("google")) {
            return new GoogleUser(attributes);
        } else if (registrationId.equalsIgnoreCase("kakao")) {
            return new KaKaoUser(attributes);
        } else {
            throw new IllegalArgumentException("Login with " + registrationId + " is not supported yet.");
        }
    }
}

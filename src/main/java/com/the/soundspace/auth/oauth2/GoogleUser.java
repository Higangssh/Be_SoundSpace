package com.the.soundspace.auth.oauth2;

import lombok.Data;

import java.util.Map;

@Data
public class GoogleUser implements OauthUserInfo {
    private Map<String, Object> attributes;

    public GoogleUser(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

}

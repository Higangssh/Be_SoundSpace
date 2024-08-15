package com.the.soundspace.config.properties;


import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtPropertiesConfig {

    private TokenProperty accessToken;
    private TokenProperty refreshToken;

    @Data
    public static class TokenProperty{
        String secret;
        Integer expiration;
    }

}

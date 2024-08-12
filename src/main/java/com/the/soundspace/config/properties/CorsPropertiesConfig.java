package com.the.soundspace.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "cors")
public class CorsPropertiesConfig {
    private String[] allowedOrigins;
}

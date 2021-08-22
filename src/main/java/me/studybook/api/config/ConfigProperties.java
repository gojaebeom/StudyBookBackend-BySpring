package me.studybook.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my")
@Getter
@Setter
public class ConfigProperties {
    private String tokenSecret;
    private String naverClientId;
    private String naverClientSecret;
}
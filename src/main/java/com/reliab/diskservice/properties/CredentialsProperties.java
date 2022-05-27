package com.reliab.diskservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "external")
@Data
public class CredentialsProperties {
    private String clientId;
    private String oauth;
    private String filePath;
    private String tokens;
    private String appName;
    private String googleAuth;
    private String yandexAuth;
}


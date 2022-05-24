package com.reliab.diskservice.configuration;

import com.reliab.diskservice.components.EnumConverter;
import com.reliab.diskservice.properties.ExternalProperties;
import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.RestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MVCConfiguration implements WebMvcConfigurer {

    private final ExternalProperties properties;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new EnumConverter());
    }

    @Bean
    public RestClient restClient(){
        Credentials credentials = new Credentials(null, properties.getOauth());
        RestClient restClient = new RestClient(credentials);

        return restClient;
    }
}

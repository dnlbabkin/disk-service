package com.reliab.diskservice.configuration;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.reliab.diskservice.components.DriveQuickstart;
import com.reliab.diskservice.components.EnumConverter;
import com.reliab.diskservice.properties.ExternalProperties;
import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.RestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
@RequiredArgsConstructor
public class MVCConfiguration implements WebMvcConfigurer {

    private static final String APPLICATION_NAME = "disk-service";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private final DriveQuickstart driveQuickstart;
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

    @Bean
    public Drive getDrive() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, driveQuickstart.getCredential(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        return service;
    }
}

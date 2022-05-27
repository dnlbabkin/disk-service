package com.reliab.diskservice;

import com.reliab.diskservice.properties.CredentialsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CredentialsProperties.class)
public class DiskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiskServiceApplication.class, args);
    }
}

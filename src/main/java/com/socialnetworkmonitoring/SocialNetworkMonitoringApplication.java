package com.socialnetworkmonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.socialnetworkmonitoring.config")
public class SocialNetworkMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkMonitoringApplication.class, args);
    }

}

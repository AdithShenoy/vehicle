package com.eroad.vehicle.api.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Initialize Rest Template for Vehicle Application
 * 
 * @author shenoy.adith
 *
 */
@Component
public class VehicleRestTemplate {
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

package com.eroad.vehicle.api.service.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.eroad.vehicle.api.dto.TimeZoneData;
import com.eroad.vehicle.api.exception.VehicleApiValidationException;
import com.eroad.vehicle.api.service.TimeZoneService;

/**
 * Impl for {@link TimeZoneService} using Google APIs Other APIs can implement
 * TimeZoneService and provide custom impl
 * 
 * @author shenoy.adith
 *
 */
@Service(value = "google")
public class GoogleTimeZoneServiceImpl implements TimeZoneService {
    
    @Value("${timezone.url}")
    private String timeZoneUrl;
    
    @Value("${timezone.api.key}")
    private String apiKey;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public TimeZoneData getTimeZoneData(String latitude, String longitude) throws VehicleApiValidationException {
        URI uri = constructUrl(latitude, longitude);
        try {
            return restTemplate.getForObject(uri, TimeZoneData.class);
        } catch (Exception ex) {
            throw new VehicleApiValidationException("invalid coordinates");
        }
    }
    
    private URI constructUrl(String latitude, String longitude) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(timeZoneUrl)
                .queryParam("location", String.format("%s,%s", latitude, longitude)).queryParam("timestamp", 1458000000)
                .queryParam("key", apiKey);
        return builder.build().encode().toUri();
    }
    
}

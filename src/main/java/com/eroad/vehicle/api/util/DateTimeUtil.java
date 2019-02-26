package com.eroad.vehicle.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.eroad.vehicle.api.exception.VehicleApiValidationException;

/**
 * Custom Datetime util for Vehicle API
 * 
 * @author shenoy.adith
 *
 */
@Component
public class DateTimeUtil {
    
    private SimpleDateFormat dateFormatGmt;
    
    private SimpleDateFormat dateFormatLocal;
    
    @PostConstruct
    private void getUTCDateFormat() {
        // UTC
        dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        // Local time zone
        dateFormatLocal = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }
    
    /**
     * Get Localized Date time from utcDateTime and timeZoneId
     * 
     * @param utcDateTime
     *            the UTC datetime
     * @param timeZoneId
     *            the TimeZone ID
     * @return the Localized Datetime
     */
    public String getLocalTimeDataFromUTC(String utcDateTime, String timeZoneId) throws VehicleApiValidationException {
        try {
            dateFormatLocal.setTimeZone(TimeZone.getTimeZone(timeZoneId));
            return dateFormatLocal.format(dateFormatGmt.parse(utcDateTime));
        } catch (ParseException ex) {
            throw new VehicleApiValidationException("time parsing failed");
        }
    }
    
}

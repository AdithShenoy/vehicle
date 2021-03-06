package com.eroad.vehicle.api.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.opencsv.bean.CsvBindByPosition;

/**
 * DTO for Vehicle data
 * 
 * @author shenoy.adith
 *
 */
public class VehicleData {
    
    @NotBlank(message = "UTC Date time is missing")
    @CsvBindByPosition(position = 0)
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}",
            message = "UTC date time is not in valid format: yyyy-mm-dd HH:mm:ss")
    private String utcDateTime;
    @NotBlank(message = "Latitude is missing")
    @Digits(fraction = 6, integer = 10, message = "Latitude must be a number")
    @CsvBindByPosition(position = 1)
    private String latitude;
    @NotBlank(message = "Longitude is missing")
    @Digits(fraction = 6, integer = 10, message = "Longitude must be a number")
    @CsvBindByPosition(position = 2)
    private String longitude;
    
    private int lineNumber;
    
    /**
     * @return the utcDateTime
     */
    public String getUtcDateTime() {
        return utcDateTime;
    }
    
    /**
     * @param utcDateTime
     *            the utcDateTime to set
     */
    public void setUtcDateTime(String utcDateTime) {
        this.utcDateTime = utcDateTime;
    }
    
    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }
    
    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }
    
    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    /**
     * @return the lineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }
    
    /**
     * @param lineNumber
     *            the lineNumber to set
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}

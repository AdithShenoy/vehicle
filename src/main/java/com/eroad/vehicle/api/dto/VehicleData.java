package com.eroad.vehicle.api.dto;

import javax.validation.constraints.NotBlank;

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
    private String utcDateTime;
    @NotBlank(message = "Latitude is missing")
    @CsvBindByPosition(position = 1)
    private String latitude;
    @NotBlank(message = "Longitude is missing")
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

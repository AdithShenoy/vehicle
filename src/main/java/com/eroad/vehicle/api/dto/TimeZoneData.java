package com.eroad.vehicle.api.dto;

import java.io.Serializable;

/**
 * DTO for time zone data
 * 
 * @author shenoy.adith
 *
 */
public class TimeZoneData implements Serializable {
    
    private static final long serialVersionUID = 6488032984927040528L;
    
    private String timeZoneId;
    
    /**
     * @return the timeZoneId
     */
    public String getTimeZoneId() {
        return timeZoneId;
    }
    
    /**
     * @param timeZoneId
     *            the timeZoneId to set
     */
    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }
}

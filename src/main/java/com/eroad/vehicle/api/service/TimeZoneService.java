package com.eroad.vehicle.api.service;

import com.eroad.vehicle.api.dto.TimeZoneData;
import com.eroad.vehicle.api.exception.VehicleApiValidationException;

/**
 * Service for Time Zone
 * 
 * @author shenoy.adith
 *
 */
public interface TimeZoneService {
    
    /**
     * Get Timezone data from latitude & longitude
     * 
     * @param latitude
     *            the Latitude
     * @param longitude
     *            the Longitude
     * @return {@link TimeZoneData}
     */
    TimeZoneData getTimeZoneData(String latitude, String longitude) throws VehicleApiValidationException;
}

package com.eroad.vehicle.api.exception;

/**
 * Custom exception for Vehicle API
 * 
 * @author shenoy.adith
 *
 */
public class VehicleApiException extends Exception {
    
    private static final long serialVersionUID = -5217834145912483868L;
    
    public VehicleApiException(String message) {
        super(message);
    }
    
    public VehicleApiException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}

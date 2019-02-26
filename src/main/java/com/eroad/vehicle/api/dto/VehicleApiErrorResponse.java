package com.eroad.vehicle.api.dto;

import java.util.List;

/**
 * DTO for error response
 * 
 * @author shenoy.adith
 *
 */
public class VehicleApiErrorResponse {
    
    private String message;
    private String resource;
    private List<VehicleApiError> errors;
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * @return the resource
     */
    public String getResource() {
        return resource;
    }
    
    /**
     * @param resource
     *            the resource to set
     */
    public void setResource(String resource) {
        this.resource = resource;
    }
    
    /**
     * @return the errors
     */
    public List<VehicleApiError> getErrors() {
        return errors;
    }
    
    /**
     * @param errors
     *            the errors to set
     */
    public void setErrors(List<VehicleApiError> errors) {
        this.errors = errors;
    }
}

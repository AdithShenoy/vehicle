package com.eroad.vehicle.api.dto;

/**
 * DTO for error object
 * 
 * @author shenoy.adith
 *
 */
public class VehicleApiError {
    
    private String field;
    private String code;
    private String message;
    
    /**
     * @return the field
     */
    public String getField() {
        return field;
    }
    
    /**
     * @param field
     *            the field to set
     */
    public void setField(String field) {
        this.field = field;
    }
    
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
    
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
}

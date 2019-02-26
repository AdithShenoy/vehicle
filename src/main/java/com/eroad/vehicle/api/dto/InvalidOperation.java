package com.eroad.vehicle.api.dto;

public class InvalidOperation {

    private Integer lineNumber;
    private String reason;
    
    public InvalidOperation(Integer lineNumber, String errorMessage) {
        this.lineNumber = lineNumber;
        this.reason = errorMessage;
    }
    
    public Integer getLineNumber() {
        return lineNumber;
    }
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    
}

package com.eroad.vehicle.api.exception;

import java.util.List;

import com.eroad.vehicle.api.dto.InvalidOperation;

public class VehicleApiValidationException extends VehicleApiException {
    
    private static final long serialVersionUID = 7940710403278601639L;
    
    private List<InvalidOperation> invalidOperationList;
    
    public VehicleApiValidationException(String errorMessage) {
        super(errorMessage);
    }
    
    public VehicleApiValidationException(List<InvalidOperation> invalidOperationList) {
        super("CSV file validation failed");
        this.invalidOperationList = invalidOperationList;
    }
    
    public List<InvalidOperation> getInvalidOperationList() {
        return invalidOperationList;
    }
}

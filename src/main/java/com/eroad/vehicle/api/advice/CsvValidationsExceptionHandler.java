package com.eroad.vehicle.api.advice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eroad.vehicle.api.dto.InvalidOperation;
import com.eroad.vehicle.api.dto.VehicleApiError;
import com.eroad.vehicle.api.dto.VehicleApiErrorResponse;
import com.eroad.vehicle.api.exception.VehicleApiException;
import com.eroad.vehicle.api.exception.VehicleApiValidationException;

/**
 * Custom exception handlers for Vehicle API exceptions
 * 
 * @author shenoy.adith
 *
 */
@RestControllerAdvice
public class CsvValidationsExceptionHandler {
    
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<VehicleApiErrorResponse> constraintViolation(
            ConstraintViolationException constraintViolationException, HttpServletRequest request) {
        List<VehicleApiError> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
            VehicleApiError error = new VehicleApiError();
            error.setMessage(violation.getMessage());
            getField(violation, error);
            error.setCode("invalid");
            errors.add(error);
        }
        return errorResponse("CSV validation Failed", request.getServletPath(), errors, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ VehicleApiValidationException.class })
    public ResponseEntity<VehicleApiErrorResponse> constraintViolation(VehicleApiValidationException exception,
            HttpServletRequest request) {
        
        List<VehicleApiError> errors = new ArrayList<>();
        
        for (InvalidOperation invalidOperation : exception.getInvalidOperationList()) {
            VehicleApiError error = new VehicleApiError();
            error.setMessage(invalidOperation.getReason());
            error.setField("line " + invalidOperation.getLineNumber());
            error.setCode("invalid");
            errors.add(error);
        }
        
        return errorResponse("CSV validation Failed", request.getServletPath(), errors, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ VehicleApiException.class })
    public ResponseEntity<VehicleApiErrorResponse> systemException(VehicleApiException exception,
            HttpServletRequest request) {
        List<VehicleApiError> errors = new ArrayList<>();
        VehicleApiError error = new VehicleApiError();
        error.setMessage(exception.getMessage());
        error.setCode("system error occurred");
        errors.add(error);
        return errorResponse("System error occurred", request.getServletPath(), errors,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    protected ResponseEntity<VehicleApiErrorResponse> errorResponse(String message, String resource,
            List<VehicleApiError> errors, HttpStatus status) {
        VehicleApiErrorResponse response = new VehicleApiErrorResponse();
        response.setMessage(message);
        response.setResource(resource);
        response.setErrors(errors);
        return ResponseEntity.status(status).body(response);
    }
    
    private void getField(ConstraintViolation<?> violation, VehicleApiError error) {
        if (violation.getPropertyPath() != null) {
            String propertyPath = violation.getPropertyPath().toString();
            int index = -1;
            if ((index = propertyPath.lastIndexOf('.')) != -1) {
                error.setField(propertyPath.substring(index + 1));
            } else {
                error.setField(propertyPath);
            }
        }
    }
    
}

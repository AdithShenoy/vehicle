package com.eroad.vehicle.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.eroad.vehicle.api.dto.InvalidOperation;
import com.eroad.vehicle.api.dto.TimeZoneData;
import com.eroad.vehicle.api.dto.VehicleData;
import com.eroad.vehicle.api.exception.VehicleApiValidationException;
import com.eroad.vehicle.api.service.TimeZoneService;

/**
 * CSV processor for vehicle data
 * 
 * @author shenoy.adith
 *
 */
public class CsvProcessor {
    
    private TimeZoneService timeZoneService;
    private DateTimeUtil dateTimeUtil;
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private List<InvalidOperation> invalidOperationList = new ArrayList<>();
    
    public CsvProcessor(TimeZoneService timeZoneService, DateTimeUtil dateTimeUtil) {
        this.timeZoneService = timeZoneService;
        this.dateTimeUtil = dateTimeUtil;
    }
    
    /**
     * Process each line of csv data and localize
     * 
     * @param vehicleData
     *            {@link VehicleData}
     * @return the CSV line to write
     */
    public String[] process(VehicleData vehicleData) {
        try {
            validateConstraintViolations(vehicleData);
            TimeZoneData timezoneData = getTimeZoneData(vehicleData);
            return new String[] { vehicleData.getUtcDateTime(), vehicleData.getLatitude(), vehicleData.getLongitude(),
                    timezoneData.getTimeZoneId(),
                    getLocalizedDateTime(vehicleData.getUtcDateTime(), timezoneData.getTimeZoneId()) };
        } catch (VehicleApiValidationException ex) {
            InvalidOperation invalidOperation = new InvalidOperation(vehicleData.getLineNumber(), ex.getMessage());
            this.invalidOperationList.add(invalidOperation);
            return null;
        }
    }
    
    private void validateConstraintViolations(VehicleData vehicleData) throws VehicleApiValidationException {
        Set<ConstraintViolation<VehicleData>> violations = this.validator.validate(vehicleData);
        if (!violations.isEmpty()) {
            StringBuilder errorMessageBuilder = new StringBuilder();
            violations.stream().forEach(violation -> errorMessageBuilder.append(violation.getMessage()).append("; "));
            throw new VehicleApiValidationException(errorMessageBuilder.toString());
        }
    }
    
    /**
     * Get Localized Time based on timezone data
     * 
     * @param utcTime
     *            the UTC time
     * @param timezoneId
     *            the TimeZone ID
     * @return Localized DateTime
     */
    private String getLocalizedDateTime(String utcTime, String timezoneId) throws VehicleApiValidationException {
        return dateTimeUtil.getLocalTimeDataFromUTC(utcTime, timezoneId);
    }
    
    /**
     * Get TimeZone data based on longitude and latitude
     * 
     * @param vehicleData
     *            {@link VehicleData}
     * @return {@link TimeZoneData}
     */
    private TimeZoneData getTimeZoneData(VehicleData vehicleData) throws VehicleApiValidationException {
        return timeZoneService.getTimeZoneData(vehicleData.getLatitude(), vehicleData.getLongitude());
    }
    
    /**
     * @return the invalidOperationList
     */
    public List<InvalidOperation> getInvalidOperationList() {
        return invalidOperationList;
    }
    
    /**
     * @param invalidOperationList
     *            the invalidOperationList to set
     */
    public void setInvalidOperationList(List<InvalidOperation> invalidOperationList) {
        this.invalidOperationList = invalidOperationList;
    }
}

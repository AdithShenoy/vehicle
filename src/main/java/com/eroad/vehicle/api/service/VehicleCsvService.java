package com.eroad.vehicle.api.service;

import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

import com.eroad.vehicle.api.exception.VehicleApiException;

/**
 * Service for Vehicle CSV function
 * 
 * @author shenoy.adith
 *
 */
public interface VehicleCsvService {
    
    /**
     * Localize data for Vehicle
     * 
     * @param file
     *            {@link MultipartFile}
     * @param outputStream
     *            {@link OutputStream}
     */
    void localizeVehicleData(MultipartFile file, OutputStream outputStream) throws VehicleApiException;
}

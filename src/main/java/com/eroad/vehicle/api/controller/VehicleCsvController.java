package com.eroad.vehicle.api.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eroad.vehicle.api.annotations.HasLineNumber;
import com.eroad.vehicle.api.service.VehicleCsvService;

/**
 * Controller class for Vehicle CSV APIs
 * 
 * @author shenoy.adith
 *
 */
@RestController
@Validated
public class VehicleCsvController {
    
    @Autowired
    private VehicleCsvService vehicleCsvService;
    
    @PostMapping(value = "/localize-vehicle-data",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void localize(@RequestParam("file") 
                @HasLineNumber(min = 2, max = 1000)
                MultipartFile file, HttpServletResponse response) throws Exception {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"vehicles.csv\"");
        
        this.vehicleCsvService.localizeVehicleData(file, response.getOutputStream());
    }
}

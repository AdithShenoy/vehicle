package com.eroad.vehicle.api.service.impl;

import java.time.ZoneId;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.eroad.vehicle.api.dto.TimeZoneData;
import com.eroad.vehicle.api.exception.VehicleApiValidationException;
import com.eroad.vehicle.api.service.TimeZoneService;

import net.iakovlev.timeshape.TimeZoneEngine;

@Service(value = "timeshape")
public class TimeShapeTimeZoneServiceImpl implements TimeZoneService {
    
    private TimeZoneEngine engine;
    
    @PostConstruct
    private void initializeEngine() {
        engine = TimeZoneEngine.initialize();
    }
    
    @Override
    public TimeZoneData getTimeZoneData(String latitude, String longitude) throws VehicleApiValidationException {
        TimeZoneData data = new TimeZoneData();
        Optional<ZoneId> zoneId = engine.query(Double.valueOf(latitude), Double.valueOf(longitude));
        if (zoneId.isPresent()) {
            data.setTimeZoneId(zoneId.get().toString());
        } else {
            throw new VehicleApiValidationException("invalid coordinates");
        }
        return data;
    }
    
}

package com.eroad.vehicle.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eroad.vehicle.api.service.TimeZoneService;

/**
 * Processor factory for {@link CsvProcessor}
 * 
 * @author shenoy.adith
 *
 */
@Component
public class CsvProcessorFactory {
    
    @Autowired
    @Qualifier("timeshape")
    private TimeZoneService timeZoneService;
    
    @Autowired
    private DateTimeUtil dateTimeUtil;
    
    /**
     * Construct new {@link CsvProcessor}
     * 
     * @return {@link CsvProcessor}
     */
    public CsvProcessor constructNewProcessor() {
        return new CsvProcessor(this.timeZoneService, this.dateTimeUtil);
    }
}

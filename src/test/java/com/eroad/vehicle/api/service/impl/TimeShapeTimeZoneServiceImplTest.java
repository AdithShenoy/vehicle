package com.eroad.vehicle.api.service.impl;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.eroad.vehicle.api.dto.TimeZoneData;
import com.eroad.vehicle.api.exception.VehicleApiValidationException;

import net.iakovlev.timeshape.TimeZoneEngine;

/**
 * Test class for {@link TimeShapeTimeZoneServiceImpl}
 * 
 * @author shenoy.adith
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeShapeTimeZoneServiceImplTest {
    
    @InjectMocks
    private TimeShapeTimeZoneServiceImpl service;
    
    @Before
    public void setEncoding() {
        ReflectionTestUtils.setField(this.service, "engine", TimeZoneEngine.initialize());
    }
    
    @Test
    public void testGetTimeZoneData() throws Exception {
        TimeZoneData data = this.service.getTimeZoneData("-44.490947", "171.220966");
        assertEquals("Pacific/Auckland", data.getTimeZoneId());
    }
    
    @Test
    public void testInvalidData() throws Exception {
        assertThatExceptionOfType(VehicleApiValidationException.class)
        .isThrownBy(() -> this.service.getTimeZoneData("111111", "111111"))
        .withMessageContaining("invalid coordinates");
    }
    
}

package com.eroad.vehicle.api.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.eroad.vehicle.api.dto.TimeZoneData;

/**
 * Test class for {@link TimeZoneServiceImpl}
 * 
 * @author shenoy.adith
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeZoneServiceImplTest {
    
    @InjectMocks
    private GoogleTimeZoneServiceImpl service;
    
    @Mock
    private RestTemplate restTemplate;
    
    @Before
    public void setEncoding() {
        ReflectionTestUtils.setField(this.service, "timeZoneUrl", "http://mockurl");
        ReflectionTestUtils.setField(this.service, "apiKey", "mockApiKey");
    }
    
    @Test
    public void testGetTimeZoneData() throws Exception {
        Mockito.when(restTemplate.getForObject(Mockito.any(), Mockito.same(TimeZoneData.class)))
                .thenReturn(createMockData());
        TimeZoneData data = this.service.getTimeZoneData("-44.490947", "171.220966");
        
        assertEquals("Pacific/Auckland", data.getTimeZoneId());
    }
    
    private TimeZoneData createMockData() {
        TimeZoneData data = new TimeZoneData();
        data.setTimeZoneId("Pacific/Auckland");
        return data;
    }
}

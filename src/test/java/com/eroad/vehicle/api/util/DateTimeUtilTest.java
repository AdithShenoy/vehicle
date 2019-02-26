package com.eroad.vehicle.api.util;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.eroad.vehicle.api.exception.VehicleApiException;

/**
 * Test class for {@link DateTimeUtil}
 * 
 * @author shenoy.adith
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DateTimeUtilTest {
    
    @InjectMocks
    private DateTimeUtil util;
    
    @Before
    public void prepare() {
        ReflectionTestUtils.setField(util, "dateFormatGmt", getUTCDateFormat());
        ReflectionTestUtils.setField(util, "dateFormatLocal", getLocalDateFormat());
    }
    
    @Test
    public void testValidValues() throws VehicleApiException {
        String localTime = util.getLocalTimeDataFromUTC("2013-07-10 02:52:49", "Pacific/Auckland");
        assertEquals("2013-07-10T14:52:49", localTime);
    }
    
    @Test
    public void testInvalidTime() throws VehicleApiException {
        assertThatExceptionOfType(VehicleApiException.class)
                .isThrownBy(() -> util.getLocalTimeDataFromUTC("2013-07-10", "Pacific/Auckland"))
                .withMessageContaining("time parsing failed");
    }
    
    @Test
    public void testInvalidTimeZone() throws VehicleApiException {
        String localTime = util.getLocalTimeDataFromUTC("2013-07-10 02:52:49", "mockTimeZone");
        assertNotEquals("2013-07-10T14:52:49", localTime);
    }
    
    private SimpleDateFormat getUTCDateFormat() {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormatGmt;
    }
    
    private SimpleDateFormat getLocalDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }
}

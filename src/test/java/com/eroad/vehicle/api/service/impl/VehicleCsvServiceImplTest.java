package com.eroad.vehicle.api.service.impl;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.eroad.vehicle.api.exception.VehicleApiException;
import com.eroad.vehicle.api.service.TimeZoneService;
import com.eroad.vehicle.api.util.CsvProcessor;
import com.eroad.vehicle.api.util.CsvProcessorFactory;
import com.eroad.vehicle.api.util.CsvUtil;
import com.eroad.vehicle.api.util.DateTimeUtil;

/**
 * Test class for {@link VehicleCsvServiceImpl}
 * 
 * @author shenoy.adith
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class VehicleCsvServiceImplTest {
    
    @InjectMocks
    private VehicleCsvServiceImpl service;
    
    @Mock
    private CsvUtil csvUtil;
    
    @Mock
    private CsvProcessorFactory processorFactory;
    
    @Mock
    private TimeZoneService timeZoneService;
    
    @Mock
    private DateTimeUtil dateTimeUtil;
    
    @Mock
    private CsvProcessor processor;
    
    @Before
    public void setEncoding() {
        ReflectionTestUtils.setField(this.service, "csvFileEncoding", "UTF-8");
        Mockito.when(processorFactory.constructNewProcessor()).thenReturn(processor);
    }
    
    @Test
    public void testLocalize() throws Exception {
        // Given
        MultipartFile file = new MockMultipartFile("name", "original_name", "text/csv",
                "2013-07-10 02:52:49,-44.490947,171.220966".getBytes());
        Mockito.doCallRealMethod().when(csvUtil).parse(Mockito.any(), Mockito.any());
        Mockito.doCallRealMethod().when(csvUtil).writer(Mockito.any());
        
        Mockito.when(processor.process(Mockito.any())).thenReturn(new String[] { "2013-07-10 02:52:49", "-44.490947",
                "171.220966", "Pacific/Auckland", "2013-07-10T14:52:49" });
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        this.service.localizeVehicleData(file, stream);
        
        String csvOutput = new String(stream.toByteArray());
        assertThat(csvOutput,
                containsString("2013-07-10 02:52:49,-44.490947,171.220966,Pacific/Auckland,2013-07-10T14:52:49"));
    }
    
    @Test
    public void testException() throws Exception {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        Mockito.when(csvUtil.parse(Mockito.any(), Mockito.anyString()))
                .thenThrow(new VehicleApiException("test_message"));
        
        assertThatExceptionOfType(VehicleApiException.class)
                .isThrownBy(() -> this.service.localizeVehicleData(file, new ByteArrayOutputStream()))
                .withMessageContaining("test_message");
    }
}

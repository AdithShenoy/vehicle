package com.eroad.vehicle.api.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.eroad.vehicle.api.annotations.HasValidHeader;

/**
 * Custom validator class for csv header check
 * 
 * @author shenoy.adith
 *
 */
@Component
public class CsvHeaderValidator implements ConstraintValidator<HasValidHeader, MultipartFile> {
    
    @Value("${csv.file.encoding:UTF-8}")
    private String encoding;
    
    @Value("${csv.file.header}")
    private String header;
    
    private String expectedHeader;
    
    @Override
    public void initialize(HasValidHeader validHeaderAnnotation) {
        this.expectedHeader = header;
    }
    
    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        String csvHeader = null;
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(multipartFile.getInputStream(), this.encoding))) {
            // Override this method must not throw IOException.
            csvHeader = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return expectedHeader.equals(csvHeader);
    }
    
}

package com.eroad.vehicle.api.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.eroad.vehicle.api.annotations.HasLineNumber;

/**
 * Custom validator class for line number check
 * 
 * @author shenoy.adith
 *
 */
@Component
public class LineNumberValidator implements ConstraintValidator<HasLineNumber, MultipartFile> {
    
    @Value("${csv.file.encoding:UTF-8}")
    private String csvFileEncoding;
    
    private int minLineNumber;
    private int maxLineNumber;
    
    @Override
    public void initialize(HasLineNumber hasLineNumberAnnotation) {
        this.minLineNumber = hasLineNumberAnnotation.min();
        this.maxLineNumber = hasLineNumberAnnotation.max();
    }
    
    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        int lineNumber = 0;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(multipartFile.getInputStream(), this.csvFileEncoding))) {
            
            // Stop reading the file when line number exceeds max.
            while (Objects.nonNull(reader.readLine()) && lineNumber <= this.maxLineNumber) {
                lineNumber++;
            }
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return !(lineNumber < this.minLineNumber || lineNumber > this.maxLineNumber);
    }
    
}

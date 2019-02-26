package com.eroad.vehicle.api.util;

import org.springframework.util.StringUtils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

/**
 * Custom converter for Vehicle CSV Data
 * 
 * @author shenoy.adith
 */
public class CsvConverter<T> extends AbstractBeanField<T> {
    
    @Override
    protected Object convert(String value)
            throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return StringUtils.isEmpty(value) ? new CsvConstraintViolationException("line ") : value;
    }
}

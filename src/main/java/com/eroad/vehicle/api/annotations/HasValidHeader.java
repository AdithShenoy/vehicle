package com.eroad.vehicle.api.annotations;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.eroad.vehicle.api.validator.CsvHeaderValidator;

/**
 * Csv header validator
 * 
 * @author shenoy.adith
 *
 */
@Target({ PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { CsvHeaderValidator.class })
public @interface HasValidHeader {
    
    String message() default "File has invalid header";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}


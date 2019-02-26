package com.eroad.vehicle.api.annotations;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.eroad.vehicle.api.validator.LineNumberValidator;

/**
 * Line number validator
 * 
 * @author shenoy.adith
 *
 */
@Target( { PARAMETER } )
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { LineNumberValidator.class } )
public @interface HasLineNumber {

    String message() default "Number of lines in the csv file should be between {min} and {max}";
    
    int min() default 0;
    int max() default Integer.MAX_VALUE;
    
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

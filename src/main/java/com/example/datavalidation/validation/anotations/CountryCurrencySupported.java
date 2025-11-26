package com.example.datavalidation.validation.anotations;

import com.example.datavalidation.validation.CountryCurrencyValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target (ElementType.TYPE)
@Retention (RetentionPolicy.RUNTIME)
@Constraint (validatedBy = CountryCurrencyValidator.class)
public @interface CountryCurrencySupported
{

    String message() default "Currency is not supported for the given country";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

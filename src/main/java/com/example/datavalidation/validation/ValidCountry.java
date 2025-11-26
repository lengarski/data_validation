package com.example.datavalidation.validation;

import com.example.datavalidation.validation.enums.CountryFormat;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target ({ElementType.FIELD, ElementType.PARAMETER})
@Retention (RetentionPolicy.RUNTIME)
@Constraint (validatedBy = ValidCountryValidator.class)
public @interface ValidCountry
{

    String message() default "Unsupported country code";

    CountryFormat format() default CountryFormat.ISO2;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

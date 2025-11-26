package com.example.datavalidation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidSortCodeValidator.class)
public @interface ValidSortCode {

    String message() default "Invalid sort code";

    SortCodeRegion[] regions() default {SortCodeRegion.UK};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

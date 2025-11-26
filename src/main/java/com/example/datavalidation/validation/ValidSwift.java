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
@Constraint(validatedBy = ValidSwiftValidator.class)
public @interface ValidSwift {

    String message() default "Invalid or unsupported SWIFT code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

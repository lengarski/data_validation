package com.example.datavalidation.validation.anotations;

import com.example.datavalidation.validation.ValidIbanValidator;

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
@Constraint (validatedBy = ValidIbanValidator.class)
public @interface ValidIban
{

    String message() default "IBAN is not valid for the supplied country";

    String countryField();

    String ibanField();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

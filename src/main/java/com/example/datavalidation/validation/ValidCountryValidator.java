package com.example.datavalidation.validation;

import com.example.datavalidation.service.CountryDirectoryService;
import com.example.datavalidation.validation.enums.CountryFormat;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class ValidCountryValidator
        implements ConstraintValidator<ValidCountry, String>
{

    private final CountryDirectoryService countryDirectoryService;
    private CountryFormat format;

    public ValidCountryValidator(CountryDirectoryService countryDirectoryService)
    {
        this.countryDirectoryService = countryDirectoryService;
    }

    @Override
    public void initialize(ValidCountry constraintAnnotation)
    {
        this.format = constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if (value == null || value.isBlank())
        {
            return true;
        }
        return countryDirectoryService.existsByFormat(format, value);
    }
}

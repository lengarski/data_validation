package com.example.datavalidation.validation;

import com.example.datavalidation.model.CurrencyCode;
import com.example.datavalidation.service.CountryDirectoryService;
import com.example.datavalidation.dto.DataEntryRequest;
import com.example.datavalidation.validation.anotations.CountryCurrencySupported;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountryCurrencyValidator
        implements ConstraintValidator<CountryCurrencySupported, DataEntryRequest>
{

    private final CountryDirectoryService countryDirectoryService;

    @Override
    public boolean isValid(DataEntryRequest value, ConstraintValidatorContext context)
    {
        if (value == null)
        {
            return true;
        }
        String country = value.getCountry();
        CurrencyCode currency = value.getCurrency();
        if (country == null || country.isBlank() || currency == null)
        {
            return true;
        }
        return countryDirectoryService.isCurrencyAllowedForCountry(country, currency) || buildViolation(context, country);
    }

    private boolean buildViolation(ConstraintValidatorContext context, String country)
    {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Currency is not supported for country: " + country).addPropertyNode("currency").addConstraintViolation();
        return false;
    }
}

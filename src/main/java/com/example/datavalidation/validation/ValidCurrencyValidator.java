package com.example.datavalidation.validation;

import com.example.datavalidation.model.CurrencyCode;
import com.example.datavalidation.repository.CurrencyRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidCurrencyValidator
        implements ConstraintValidator<ValidCurrency, String>
{

    private final CurrencyRepository currencyRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if (value == null || value.isBlank())
        {
            return true;
        }
        try
        {
            CurrencyCode code = CurrencyCode.fromValue(value);
            return currencyRepository.existsByCode(code);
        }
        catch (IllegalArgumentException ex)
        {
            return false;
        }
    }
}

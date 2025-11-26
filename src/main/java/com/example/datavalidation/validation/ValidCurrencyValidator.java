package com.example.datavalidation.validation;

import com.example.datavalidation.model.CurrencyCode;
import com.example.datavalidation.repository.CurrencyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ValidCurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    private final CurrencyRepository currencyRepository;

    public ValidCurrencyValidator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        try {
            CurrencyCode code = CurrencyCode.fromValue(value);
            return currencyRepository.existsByCode(code);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}

package com.example.datavalidation.validation;

import com.example.datavalidation.repository.BankIdentifierRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidBicValidator
        implements ConstraintValidator<ValidBic, String>
{

    private static final Pattern BIC_PATTERN = Pattern.compile("^[A-Z]{4}[A-Z]{2}[A-Z0-9]{2}([A-Z0-9]{3})?$");

    private final BankIdentifierRepository bankIdentifierRepository;

    public ValidBicValidator(BankIdentifierRepository bankIdentifierRepository)
    {
        this.bankIdentifierRepository = bankIdentifierRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if (value == null || value.isBlank())
        {
            return true;
        }
        String normalized = value.trim().toUpperCase();
        if (!BIC_PATTERN.matcher(normalized).matches())
        {
            return false;
        }
        return bankIdentifierRepository.existsByBicIgnoreCase(normalized);
    }
}

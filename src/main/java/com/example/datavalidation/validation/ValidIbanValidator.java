package com.example.datavalidation.validation;

import com.example.datavalidation.model.Country;
import com.example.datavalidation.service.CountryDirectoryService;
import com.example.datavalidation.validation.anotations.ValidIban;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
public class ValidIbanValidator
        implements ConstraintValidator<ValidIban, Object>
{

    private final CountryDirectoryService countryDirectoryService;
    private String countryField;
    private String ibanField;

    public ValidIbanValidator(CountryDirectoryService countryDirectoryService)
    {
        this.countryDirectoryService = countryDirectoryService;
    }

    @Override
    public void initialize(ValidIban constraintAnnotation)
    {
        this.countryField = constraintAnnotation.countryField();
        this.ibanField = constraintAnnotation.ibanField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context)
    {
        if (value == null)
        {
            return true;
        }
        BeanWrapperImpl wrapper = new BeanWrapperImpl(value);
        String countryIso2 = (String) wrapper.getPropertyValue(countryField);
        String iban = (String) wrapper.getPropertyValue(ibanField);
        if (!StringUtils.hasText(countryIso2) || !StringUtils.hasText(iban))
        {
            return true;
        }
        Optional<Country> countryOpt = countryDirectoryService.findByIso2(countryIso2);
        if (countryOpt.isEmpty())
        {
            return true;
        }
        Country country = countryOpt.get();
        String normalizedIban = iban.replaceAll("\\s+", "").toUpperCase();
        boolean matchesCountry = normalizedIban.startsWith(country.getIso2());
        boolean matchesPattern = country.getIbanRegex() == null || normalizedIban.matches(country.getIbanRegex());
        if (matchesCountry && matchesPattern)
        {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode(ibanField).addConstraintViolation();
        return false;
    }
}

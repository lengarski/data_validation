package com.example.datavalidation.validation;

import com.example.datavalidation.validation.enums.SortCodeRegion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ValidSortCodeValidator
        implements ConstraintValidator<ValidSortCode, String>
{

    private final List<Pattern> allowedPatterns = new ArrayList<>();

    @Override
    public void initialize(ValidSortCode constraintAnnotation)
    {
        allowedPatterns.clear();
        for(SortCodeRegion region : constraintAnnotation.regions())
        {
            allowedPatterns.add(patternFor(region));
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if (value == null || value.isBlank())
        {
            return true;
        }
        String normalized = value.replaceAll("[\\s-]", "");
        return allowedPatterns.stream().anyMatch(pattern -> pattern.matcher(normalized).matches());
    }

    private Pattern patternFor(SortCodeRegion region)
    {
        return switch (region)
        {
            case UK -> Pattern.compile("^\\d{6}$");
            case US -> Pattern.compile("^\\d{9}$");
        };
    }
}

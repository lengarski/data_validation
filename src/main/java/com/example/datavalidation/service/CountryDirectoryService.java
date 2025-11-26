package com.example.datavalidation.service;

import com.example.datavalidation.model.Country;
import com.example.datavalidation.model.CurrencyCode;
import com.example.datavalidation.repository.CountryRepository;
import com.example.datavalidation.validation.enums.CountryFormat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@Transactional (readOnly = true)
public class CountryDirectoryService
{

    private final CountryRepository countryRepository;

    public CountryDirectoryService(CountryRepository countryRepository)
    {
        this.countryRepository = countryRepository;
    }

    public boolean isCurrencyAllowedForCountry(String countryName, CurrencyCode currency)
    {
        if (!StringUtils.hasText(countryName) || currency == null)
        {
            return false;
        }
        return countryRepository.findByNameIgnoreCase(countryName).map(country -> country.getCurrency().getCode() == currency).orElse(false);
    }

    public Optional<Country> findByIso2(String iso2)
    {
        if (!StringUtils.hasText(iso2))
        {
            return Optional.empty();
        }
        return countryRepository.findByIso2IgnoreCase(iso2);
    }

    public Optional<Country> findByIso3(String iso3)
    {
        if (!StringUtils.hasText(iso3))
        {
            return Optional.empty();
        }
        return countryRepository.findByIso3IgnoreCase(iso3);
    }

    public boolean existsByFormat(CountryFormat format, String value)
    {
        if (!StringUtils.hasText(value))
        {
            return false;
        }
        return switch (format)
        {
            case ISO2 -> countryRepository.existsByIso2IgnoreCase(value);
            case ISO3 -> countryRepository.existsByIso3IgnoreCase(value);
        };
    }
}

package com.example.datavalidation.config;

import com.example.datavalidation.model.BankIdentifier;
import com.example.datavalidation.model.Country;
import com.example.datavalidation.model.Currency;
import com.example.datavalidation.model.CurrencyCode;
import com.example.datavalidation.repository.BankIdentifierRepository;
import com.example.datavalidation.repository.CountryRepository;
import com.example.datavalidation.repository.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBootstrap {

    @Bean
    CommandLineRunner loadReferenceData(CurrencyRepository currencyRepository,
                                        CountryRepository countryRepository,
                                        BankIdentifierRepository bankIdentifierRepository) {
        return args -> {
            if (currencyRepository.count() == 0) {
                currencyRepository.save(new Currency(CurrencyCode.EUR, "Euro"));
                currencyRepository.save(new Currency(CurrencyCode.USD, "US Dollar"));
                currencyRepository.save(new Currency(CurrencyCode.GBP, "British Pound"));
                currencyRepository.save(new Currency(CurrencyCode.BGN, "Bulgarian Lev"));
            }

            if (countryRepository.count() == 0) {
                Currency eur = currencyRepository.findByCode(CurrencyCode.EUR).orElseThrow();
                Currency usd = currencyRepository.findByCode(CurrencyCode.USD).orElseThrow();
                Currency gbp = currencyRepository.findByCode(CurrencyCode.GBP).orElseThrow();
                Currency bgn = currencyRepository.findByCode(CurrencyCode.BGN).orElseThrow();

                countryRepository.save(new Country("Germany", "DE", "DEU", "+49", "Berlin", eur,
                        "^DE\\d{20}$"));
                countryRepository.save(new Country("United Kingdom", "GB", "GBR", "+44", "London", gbp,
                        "^GB\\d{2}[A-Z]{4}\\d{14}$"));
                countryRepository.save(new Country("United States", "US", "USA", "+1", "Washington", usd,
                        null));
                countryRepository.save(new Country("Bulgaria", "BG", "BGR", "+359", "Sofia", bgn,
                        "^BG\\d{2}[A-Z]{4}\\d{6}[A-Z0-9]{8}$"));
            }

            if (bankIdentifierRepository.count() == 0) {
                Country germany = countryRepository.findByIso2IgnoreCase("DE").orElseThrow();
                Country unitedKingdom = countryRepository.findByIso2IgnoreCase("GB").orElseThrow();
                Country unitedStates = countryRepository.findByIso2IgnoreCase("US").orElseThrow();

                bankIdentifierRepository.save(new BankIdentifier("DEUTDEFF", "Deutsche Bank", germany));
                bankIdentifierRepository.save(new BankIdentifier("DEUTDEFF500", "Deutsche Bank Berlin", germany));
                bankIdentifierRepository.save(new BankIdentifier("NWBKGB2L", "NatWest Bank", unitedKingdom));
                bankIdentifierRepository.save(new BankIdentifier("BOFAUS3N", "Bank of America", unitedStates));
                bankIdentifierRepository.save(new BankIdentifier("BARCGB22", "Barclays", unitedKingdom));
            }
        };
    }
}

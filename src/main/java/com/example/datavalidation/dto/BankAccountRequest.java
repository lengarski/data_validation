package com.example.datavalidation.dto;

import com.example.datavalidation.validation.enums.CountryFormat;
import com.example.datavalidation.validation.ValidBic;
import com.example.datavalidation.validation.ValidCountry;
import com.example.datavalidation.validation.ValidCurrency;
import com.example.datavalidation.validation.anotations.ValidIban;
import com.example.datavalidation.validation.ValidSortCode;
import com.example.datavalidation.validation.ValidSwift;
import com.example.datavalidation.validation.enums.SortCodeRegion;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidIban (countryField = "countryIso2", ibanField = "iban")
public class BankAccountRequest
{

    @NotBlank (message = "Account holder name is required")
    private String accountHolder;

    @NotBlank (message = "Country ISO2 code is required")
    @ValidCountry (format = CountryFormat.ISO2)
    private String countryIso2;

    @NotBlank (message = "Currency code is required")
    @ValidCurrency
    private String currencyCode;

    @NotBlank (message = "IBAN is required")
    private String iban;

    @NotBlank (message = "BIC code is required")
    @ValidBic
    private String bic;

    @ValidSwift
    private String swift;

    @ValidSortCode (regions = {SortCodeRegion.UK, SortCodeRegion.US})
    private String sortCode;

}

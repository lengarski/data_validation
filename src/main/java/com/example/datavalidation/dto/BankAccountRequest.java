package com.example.datavalidation.web.dto;

import com.example.datavalidation.validation.CountryFormat;
import com.example.datavalidation.validation.ValidBic;
import com.example.datavalidation.validation.ValidCountry;
import com.example.datavalidation.validation.ValidCurrency;
import com.example.datavalidation.validation.ValidIban;
import com.example.datavalidation.validation.ValidSortCode;
import com.example.datavalidation.validation.ValidSwift;
import com.example.datavalidation.validation.SortCodeRegion;

import jakarta.validation.constraints.NotBlank;

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

    public String getAccountHolder()
    {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder)
    {
        this.accountHolder = accountHolder;
    }

    public String getCountryIso2()
    {
        return countryIso2;
    }

    public void setCountryIso2(String countryIso2)
    {
        this.countryIso2 = countryIso2;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public String getIban()
    {
        return iban;
    }

    public void setIban(String iban)
    {
        this.iban = iban;
    }

    public String getBic()
    {
        return bic;
    }

    public void setBic(String bic)
    {
        this.bic = bic;
    }

    public String getSwift()
    {
        return swift;
    }

    public void setSwift(String swift)
    {
        this.swift = swift;
    }

    public String getSortCode()
    {
        return sortCode;
    }

    public void setSortCode(String sortCode)
    {
        this.sortCode = sortCode;
    }
}

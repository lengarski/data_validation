package com.example.datavalidation.web.dto;

import com.example.datavalidation.model.BankAccount;

public class BankAccountResponse {

    private Long id;
    private String accountHolder;
    private String country;
    private String countryIso2;
    private String countryIso3;
    private String currency;
    private String iban;
    private String bic;
    private String swift;
    private String sortCode;

    public static BankAccountResponse fromEntity(BankAccount account) {
        BankAccountResponse response = new BankAccountResponse();
        response.id = account.getId();
        response.accountHolder = account.getAccountHolder();
        response.country = account.getCountry().getName();
        response.countryIso2 = account.getCountry().getIso2();
        response.countryIso3 = account.getCountry().getIso3();
        response.currency = account.getCurrency().getCode().name();
        response.iban = account.getIban();
        response.bic = account.getBic();
        response.swift = account.getSwift();
        response.sortCode = account.getSortCode();
        return response;
    }

    public Long getId() {
        return id;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryIso2() {
        return countryIso2;
    }

    public String getCountryIso3() {
        return countryIso3;
    }

    public String getCurrency() {
        return currency;
    }

    public String getIban() {
        return iban;
    }

    public String getBic() {
        return bic;
    }

    public String getSwift() {
        return swift;
    }

    public String getSortCode() {
        return sortCode;
    }
}

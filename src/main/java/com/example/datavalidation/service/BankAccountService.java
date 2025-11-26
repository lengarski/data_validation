package com.example.datavalidation.service;

import com.example.datavalidation.model.BankAccount;
import com.example.datavalidation.model.Country;
import com.example.datavalidation.model.Currency;
import com.example.datavalidation.model.CurrencyCode;
import com.example.datavalidation.repository.BankAccountRepository;
import com.example.datavalidation.repository.CurrencyRepository;
import com.example.datavalidation.dto.BankAccountRequest;
import com.example.datavalidation.dto.BankAccountResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankAccountService
{

    private final BankAccountRepository bankAccountRepository;
    private final CurrencyRepository currencyRepository;
    private final CountryDirectoryService countryDirectoryService;

    public BankAccountService(BankAccountRepository bankAccountRepository, CurrencyRepository currencyRepository, CountryDirectoryService countryDirectoryService)
    {
        this.bankAccountRepository = bankAccountRepository;
        this.currencyRepository = currencyRepository;
        this.countryDirectoryService = countryDirectoryService;
    }

    public List<BankAccountResponse> findAll()
    {
        return bankAccountRepository.findAll().stream().map(BankAccountResponse :: fromEntity).toList();
    }

    public BankAccountResponse create(BankAccountRequest request)
    {
        BankAccount account = new BankAccount();
        applyRequest(account, request);
        BankAccount saved = bankAccountRepository.save(account);
        return BankAccountResponse.fromEntity(saved);
    }

    private void applyRequest(BankAccount account, BankAccountRequest request)
    {
        Country country = countryDirectoryService.findByIso2(request.getCountryIso2()).orElseThrow(() -> new IllegalArgumentException("Unknown country ISO2 code: " + request.getCountryIso2()));
        CurrencyCode code = CurrencyCode.fromValue(request.getCurrencyCode());
        Currency currency = currencyRepository.findByCode(code).orElseThrow(() -> new IllegalArgumentException("Currency not configured: " + code));
        account.setAccountHolder(request.getAccountHolder());
        account.setCountry(country);
        account.setCurrency(currency);
        account.setIban(formatUpper(request.getIban()));
        account.setBic(formatUpper(request.getBic()));
        account.setSwift(request.getSwift() == null ? null : formatUpper(request.getSwift()));
        account.setSortCode(request.getSortCode());
    }

    private String formatUpper(String value)
    {
        return value == null ? null : value.replaceAll("\\s+", "").toUpperCase();
    }
}

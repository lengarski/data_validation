package com.example.datavalidation.controller;

import com.example.datavalidation.service.BankAccountService;
import com.example.datavalidation.dto.BankAccountRequest;
import com.example.datavalidation.dto.BankAccountResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/api/bank-accounts")
public class BankAccountController
{

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService)
    {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public List<BankAccountResponse> listAccounts()
    {
        return bankAccountService.findAll();
    }

    @PostMapping
    public ResponseEntity<BankAccountResponse> createAccount(@Valid @RequestBody BankAccountRequest request)
    {
        BankAccountResponse created = bankAccountService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}

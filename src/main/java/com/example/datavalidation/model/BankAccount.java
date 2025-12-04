package com.example.datavalidation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "bank_accounts")
public class BankAccount
{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String accountHolder;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "country_id", nullable = false)
    private Country country;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "currency_id", nullable = false)
    private Currency currency;

    @Column (nullable = false, unique = true)
    private String iban;

    @Column (nullable = false)
    private String bic;

    @Column
    private String swift;

    @Column
    private String sortCode;

}

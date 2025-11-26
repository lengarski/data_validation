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

@Entity
@Table (name = "bank_identifiers")
public class BankIdentifier
{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true, length = 11)
    private String bic;

    @Column (nullable = false)
    private String bankName;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "country_id", nullable = false)
    private Country country;

    protected BankIdentifier()
    {
    }

    public BankIdentifier(String bic, String bankName, Country country)
    {
        this.bic = bic;
        this.bankName = bankName;
        this.country = country;
    }

    public Long getId()
    {
        return id;
    }

    public String getBic()
    {
        return bic;
    }

    public void setBic(String bic)
    {
        this.bic = bic;
    }

    public String getBankName()
    {
        return bankName;
    }

    public Country getCountry()
    {
        return country;
    }
}

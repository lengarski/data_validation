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
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true, length = 2)
    private String iso2;

    @Column(nullable = false, unique = true, length = 3)
    private String iso3;

    @Column(name = "phone_prefix", nullable = false)
    private String phonePrefix;

    @Column(nullable = false)
    private String capital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "iban_regex")
    private String ibanRegex;

    protected Country() {
    }

    public Country(String name, String iso2, String iso3, String phonePrefix, String capital, Currency currency,
                   String ibanRegex) {
        this.name = name;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.phonePrefix = phonePrefix;
        this.capital = capital;
        this.currency = currency;
        this.ibanRegex = ibanRegex;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIso2() {
        return iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public String getCapital() {
        return capital;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getIbanRegex() {
        return ibanRegex;
    }
}

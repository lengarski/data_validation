package com.example.datavalidation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "currencies")
public class Currency
{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false, unique = true, length = 3)
    private CurrencyCode code;

    @Column (nullable = false)
    private String name;

    protected Currency()
    {
    }

    public Currency(CurrencyCode code, String name)
    {
        this.code = code;
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public CurrencyCode getCode()
    {
        return code;
    }

    public void setCode(CurrencyCode code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}

package com.example.datavalidation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table (name = "data_entries")
public class DataEntry
{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "Requester is required")
    private String requester;

    @NotBlank (message = "Country is required")
    private String country;

    @NotNull (message = "Amount is required")
    @Positive
    @Min (1)
    @Max (1_000_000)
    @Column (precision = 19, scale = 2)
    private BigDecimal amount;

    @NotNull (message = "Quantity is required")
    @Min (1)
    @Max (100)
    private Integer quantity;

    @NotNull (message = "Currency is required")
    @Enumerated (EnumType.STRING)
    private CurrencyCode currency;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getRequester()
    {
        return requester;
    }

    public void setRequester(String requester)
    {
        this.requester = requester;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public CurrencyCode getCurrency()
    {
        return currency;
    }

    public void setCurrency(CurrencyCode currency)
    {
        this.currency = currency;
    }
}

package com.example.datavalidation.dto;

import com.example.datavalidation.model.CurrencyCode;
import com.example.datavalidation.validation.anotations.CountryCurrencySupported;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@CountryCurrencySupported
public class DataEntryRequest
{

    @NotBlank (message = "Requester name is required")
    private String requester;

    @NotBlank (message = "Country is required")
    private String country;

    @NotNull (message = "Amount is required")
    @Positive (message = "Amount must be positive")
    @Min (value = 1, message = "Amount must be at least 1")
    @Max (value = 1_000_000, message = "Amount must be below 1,000,000")
    private BigDecimal amount;

    @NotNull (message = "Quantity is required")
    @Min (value = 1, message = "Quantity must be at least 1")
    @Max (value = 100, message = "Quantity must be at most 100")
    private Integer quantity;

    @NotNull (message = "Currency is required")
    private CurrencyCode currency;

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

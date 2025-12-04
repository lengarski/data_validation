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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}

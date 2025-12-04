package com.example.datavalidation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
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

    public Currency(CurrencyCode code, String name)
    {
        this.code = code;
        this.name = name;
    }
}

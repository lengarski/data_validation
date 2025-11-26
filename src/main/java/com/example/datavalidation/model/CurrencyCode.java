package com.example.datavalidation.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum CurrencyCode {
    USD,
    EUR,
    GBP,
    BGN;

    @JsonCreator
    public static CurrencyCode fromValue(String value) {
        if (value == null) {
            return null;
        }
        return Arrays.stream(values())
                .filter(code -> code.name().equalsIgnoreCase(value.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported currency code: " + value));
    }
}

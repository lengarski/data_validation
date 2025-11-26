package com.example.datavalidation.repository;

import com.example.datavalidation.model.Currency;
import com.example.datavalidation.model.CurrencyCode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository
        extends JpaRepository<Currency, Long>
{

    Optional<Currency> findByCode(CurrencyCode code);

    boolean existsByCode(CurrencyCode code);
}

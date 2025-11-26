package com.example.datavalidation.repository;

import com.example.datavalidation.model.BankIdentifier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankIdentifierRepository
        extends JpaRepository<BankIdentifier, Long>
{

    boolean existsByBicIgnoreCase(String bic);
}

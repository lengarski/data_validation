package com.example.datavalidation.repository;

import com.example.datavalidation.model.Country;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository
        extends JpaRepository<Country, Long>
{

    Optional<Country> findByNameIgnoreCase(String name);

    Optional<Country> findByIso2IgnoreCase(String iso2);

    Optional<Country> findByIso3IgnoreCase(String iso3);

    boolean existsByIso2IgnoreCase(String iso2);

    boolean existsByIso3IgnoreCase(String iso3);
}

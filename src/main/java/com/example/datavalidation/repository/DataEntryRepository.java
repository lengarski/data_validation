package com.example.datavalidation.repository;

import com.example.datavalidation.model.DataEntry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataEntryRepository
        extends JpaRepository<DataEntry, Long>
{
}

package com.example.datavalidation.service;

import com.example.datavalidation.model.DataEntry;
import com.example.datavalidation.repository.DataEntryRepository;
import com.example.datavalidation.dto.DataEntryRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DataEntryService
{

    private final DataEntryRepository repository;

    public List<DataEntry> findAll()
    {
        return repository.findAll();
    }

    public DataEntry findById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("DataEntry not found: " + id));
    }

    public DataEntry create(DataEntryRequest request)
    {
        DataEntry entry = new DataEntry();
        applyRequest(entry, request);
        return repository.save(entry);
    }

    public DataEntry update(Long id, DataEntryRequest request)
    {
        DataEntry existing = findById(id);
        applyRequest(existing, request);
        return repository.save(existing);
    }

    public void delete(Long id)
    {
        DataEntry existing = findById(id);
        repository.delete(existing);
    }

    private void applyRequest(DataEntry target, DataEntryRequest request)
    {
        target.setRequester(request.getRequester());
        target.setCountry(request.getCountry());
        target.setAmount(request.getAmount());
        target.setQuantity(request.getQuantity());
        target.setCurrency(request.getCurrency());
    }
}

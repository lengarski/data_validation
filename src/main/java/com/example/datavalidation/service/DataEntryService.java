package com.example.datavalidation.service;

import com.example.datavalidation.model.DataEntry;
import com.example.datavalidation.repository.DataEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DataEntryService {

    private final DataEntryRepository repository;

    public DataEntryService(DataEntryRepository repository) {
        this.repository = repository;
    }

    public List<DataEntry> findAll() {
        return repository.findAll();
    }

    public DataEntry findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DataEntry not found: " + id));
    }

    public DataEntry create(DataEntry entry) {
        entry.setId(null);
        return repository.save(entry);
    }

    public DataEntry update(Long id, DataEntry updated) {
        DataEntry existing = findById(id);
        existing.setDataKey(updated.getDataKey());
        existing.setDataValue(updated.getDataValue());
        return repository.save(existing);
    }

    public void delete(Long id) {
        DataEntry existing = findById(id);
        repository.delete(existing);
    }
}

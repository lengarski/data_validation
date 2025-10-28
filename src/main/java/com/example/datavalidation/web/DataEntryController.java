package com.example.datavalidation.web;

import com.example.datavalidation.model.DataEntry;
import com.example.datavalidation.service.DataEntryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data-entries")
public class DataEntryController {

    private final DataEntryService service;

    public DataEntryController(DataEntryService service) {
        this.service = service;
    }

    @GetMapping
    public List<DataEntry> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DataEntry getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<DataEntry> create(@Valid @RequestBody DataEntry entry) {
        DataEntry created = service.create(entry);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public DataEntry update(@PathVariable Long id, @Valid @RequestBody DataEntry entry) {
        return service.update(id, entry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

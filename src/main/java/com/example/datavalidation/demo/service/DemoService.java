package com.example.datavalidation.demo.service;

import com.example.datavalidation.demo.dto.DemoRequest;
import com.example.datavalidation.demo.dto.DemoResponse;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public DemoResponse getDemo() {
        return new DemoResponse();
    }

    public DemoResponse createDemo(DemoRequest request) {
        return new DemoResponse();
    }
}

package com.example.datavalidation.demo.controller;

import com.example.datavalidation.demo.dto.DemoRequest;
import com.example.datavalidation.demo.dto.DemoResponse;
import com.example.datavalidation.demo.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping
    public DemoResponse getDemo() {
        return demoService.getDemo();
    }

    @PostMapping
    public DemoResponse createDemo(@RequestBody DemoRequest request) {
        return demoService.createDemo(request);
    }
}

package com.demo.controller;

import com.demo.service.ApplicationService;
import com.demo.dto.ApplicationDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/application")
@Slf4j
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/register")
    public ResponseEntity<ApplicationDto> registerApplication(@Valid @RequestBody ApplicationDto request) {
        log.info("Register Application");
        ApplicationDto applicationDto = applicationService.registerApplication(request);
        return new ResponseEntity<>(applicationDto, HttpStatus.CREATED);
    }
}

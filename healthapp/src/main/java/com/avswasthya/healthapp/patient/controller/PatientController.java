package com.avswasthya.healthapp.patient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
@Slf4j
public class PatientController {

    @GetMapping("/health-check")
    public String healthCheck() {
        log.info("Health is ok !");
        return "This is health check from PatientController";
    }
}

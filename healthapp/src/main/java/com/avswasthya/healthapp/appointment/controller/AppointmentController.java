package com.avswasthya.healthapp.appointment.controller;

import com.avswasthya.healthapp.appointment.model.Appointment;
import com.avswasthya.healthapp.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // 1. Book appointment
    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) {
        appointmentService.bookAppointment(appointment);
        return ResponseEntity.ok("Appointment booked successfully.");
    }

    // 2. Get patient appointments
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsForPatient(patientId));
    }

    // 3. Get doctor appointments
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsForDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsForDoctor(doctorId));
    }

    // 4. Cancel or update appointment
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateAppointmentStatus(
            @PathVariable Long id,
            @RequestParam String status // example: CANCELLED, COMPLETED
    ) {
        appointmentService.updateStatus(id, status);
        return ResponseEntity.ok("Appointment status updated.");
    }
}

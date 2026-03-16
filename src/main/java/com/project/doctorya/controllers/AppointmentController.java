package com.project.doctorya.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.doctorya.dtos.AppointmentDto;
import com.project.doctorya.models.Appointment;
import com.project.doctorya.services.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public Appointment create(@RequestBody AppointmentDto dto) {
        return appointmentService.create(dto);
    }

    @GetMapping("/{id}")
    public Appointment getById(@PathVariable UUID id) {
        return appointmentService.getById(id);
    }

    @PutMapping("/{id}")
    public Appointment update(@RequestBody AppointmentDto dto, @PathVariable UUID id) {
        return appointmentService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        appointmentService.delete(id);
    }
}

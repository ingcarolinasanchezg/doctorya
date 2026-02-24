package com.project.doctorya.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doctorya.dtos.DoctorDto;
import com.project.doctorya.models.Doctor;
import com.project.doctorya.services.DoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(name = "Doctors", description = "Doctor related operations")
@Validated
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Operation(summary = "Get all doctors")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Doctors retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Doctor>> getAll() {
        List<Doctor> doctors = doctorService.getAll();
        return ResponseEntity.ok(doctors);
    }

    @Operation(summary = "Get a doctor by UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Doctor found successfully"),
        @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(
            @Parameter(description = "UUID of the doctor") @PathVariable UUID id) {
        Doctor doctor = doctorService.getById(id);
        return ResponseEntity.ok(doctor);
    }

    @Operation(summary = "Get a doctor by identification")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Doctor found successfully"),
        @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/identification/{identification}")
    public ResponseEntity<Doctor> getByIdentification(
            @Parameter(description = "Identification of the doctor") @PathVariable String identification) {
        Doctor doctor = doctorService.getByIdentification(identification);
        return ResponseEntity.ok(doctor);
    }

    @Operation(summary = "Create a new doctor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Doctor created successfully"),
        @ApiResponse(responseCode = "409", description = "Doctor already exists", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody @Valid DoctorDto doctorDto) {
        Doctor response = doctorService.create(doctorDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update an existing doctor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Doctor updated successfully"),
        @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(
            @Parameter(description = "UUID of the doctor") @PathVariable UUID id,
            @RequestBody @Valid DoctorDto doctorDto) {
        Doctor response = doctorService.update(doctorDto, id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a doctor by UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Doctor deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        doctorService.delete(id);
    }

}
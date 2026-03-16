package com.project.doctorya.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.project.doctorya.dtos.AppointmentDto;
import com.project.doctorya.models.Appointment;
import com.project.doctorya.repositories.AppointmentRepository;
import com.project.doctorya.services.AppointmentService;
import java.time.LocalDateTime;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentTest {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AppointmentRepository appointmentRepository;

    @AfterEach
    void cleanDatabase() {
        appointmentRepository.deleteAll();
    }

    @Test
    @Order(1)
    void testCreateAppointment() throws Exception {
        AppointmentDto dto = new AppointmentDto();
        dto.setPatientId(UUID.randomUUID()); // ajusta si necesitas IDs reales
        dto.setDoctorId(UUID.randomUUID());
        dto.setDate(LocalDateTime.now()); // ✔ correcto
        dto.setReason("General check");   // ✔ correcto
        

        Appointment appointment = appointmentService.create(dto);

        assertNotNull(appointment);
        assertEquals(dto.getReason(), appointment.getReason());
    }

    @Test
    @Order(2)
    void testGetById() throws Exception {
        AppointmentDto dto = new AppointmentDto();
        dto.setPatientId(UUID.randomUUID());
        dto.setDoctorId(UUID.randomUUID());
        dto.setDate(LocalDateTime.now()); // ✔ correcto
        dto.setReason("Consult");

        Appointment created = appointmentService.create(dto);
        Appointment found = appointmentService.getById(created.getId());

        assertEquals(created.getId(), found.getId());
    }

    @Test
    @Order(3)
    void testUpdateAppointment() throws Exception {
        AppointmentDto dto = new AppointmentDto();
        dto.setPatientId(UUID.randomUUID());
        dto.setDoctorId(UUID.randomUUID());
        dto.setDate(LocalDateTime.now()); // ✔ correcto
        dto.setReason("Initial");

        Appointment created = appointmentService.create(dto);

        AppointmentDto updateDto = new AppointmentDto();
        updateDto.setReason("Updated reason");

        Appointment updated = appointmentService.update(updateDto, created.getId());

        assertNotNull(updated);
        assertEquals("Updated reason", updated.getReason());
    }

    @Test
    @Order(4)
    void testDeleteAppointment() throws Exception {
        AppointmentDto dto = new AppointmentDto();
        dto.setPatientId(UUID.randomUUID());
        dto.setDoctorId(UUID.randomUUID());
        dto.setDate(LocalDateTime.now()); // ✔ correcto
        dto.setReason("Delete test");

        Appointment created = appointmentService.create(dto);

        appointmentService.delete(created.getId());
    }
}
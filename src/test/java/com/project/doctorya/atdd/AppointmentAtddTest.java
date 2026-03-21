package com.project.doctorya.atdd;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.doctorya.dtos.AppointmentDto;
import com.project.doctorya.dtos.DoctorDto;
import com.project.doctorya.dtos.PatientDto;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentAtddTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAppointmentSuccessfully() throws Exception {

        // 🔹 1. Crear doctor
        DoctorDto doctor = new DoctorDto();
        doctor.setIdentification("111111111");
        doctor.setName("Dr ATDD");
        doctor.setSpecialty("Cardiology");

        String doctorResponse = mockMvc.perform(post("/doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(doctor)))
                .andReturn().getResponse().getContentAsString();

        UUID doctorId = extractId(doctorResponse);

        // 🔹 2. Crear patient
        PatientDto patient = new PatientDto();
        patient.setIdentification("222222222");
        patient.setName("Patient ATDD");
        patient.setInsurance("Sura");

        String patientResponse = mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
                .andReturn().getResponse().getContentAsString();

        UUID patientId = extractId(patientResponse);

        // 🔹 3. Crear appointment
        AppointmentDto appointment = new AppointmentDto();
        appointment.setDoctorId(doctorId);
        appointment.setPatientId(patientId);
        appointment.setDate(LocalDateTime.now().plusDays(1));
        appointment.setReason("Consulta general");

        mockMvc.perform(post("/appointment") // 👈 revisa si es singular
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appointment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.reason").value("Consulta general"));
    }

    private UUID extractId(String json) throws Exception {
        return UUID.fromString(
        objectMapper.readTree(json).get("id").asText()
     );
    }
}

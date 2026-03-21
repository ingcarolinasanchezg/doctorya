package com.project.doctorya.atdd;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.doctorya.dtos.PatientDto;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientAtddTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreatePatientSuccessfully() throws Exception {

        PatientDto patient = new PatientDto();
        patient.setIdentification("987654321");
        patient.setName("Patient ATDD");
        patient.setInsurance("Sura"); // 👈 CORRECTO SEGÚN TU MODELO

        mockMvc.perform(post("/patient") // 👈 valida si es /patient o /patients
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Patient ATDD"))
                .andExpect(jsonPath("$.identification").value("987654321"));
    }
}

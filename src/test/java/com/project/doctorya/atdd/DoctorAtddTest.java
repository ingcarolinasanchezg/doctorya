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
import com.project.doctorya.dtos.DoctorDto;

@SpringBootTest
@AutoConfigureMockMvc
public class DoctorAtddTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
void shouldCreateDoctorSuccessfully() throws Exception {

    DoctorDto doctor = new DoctorDto();
    doctor.setIdentification("123456789"); // 👈 obligatorio
    doctor.setName("Dr ATDD");
    doctor.setSpecialty("Cardiology");

    mockMvc.perform(post("/doctor")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(doctor)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value("Dr ATDD"))
            .andExpect(jsonPath("$.identification").value("123456789"));
}
}

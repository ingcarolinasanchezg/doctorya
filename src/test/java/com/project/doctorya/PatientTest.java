package com.project.doctorya;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.doctorya.dtos.PatientDto;
import com.project.doctorya.models.Patient;
import com.project.doctorya.services.PatientService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientTest {
    @Autowired
    PatientService patientService;

    @Test
    @Order(1)
    void testCreatePatient() throws Exception {
        PatientDto patientDto = new PatientDto();
        patientDto.setIdentification("1053847610");
        patientDto.setName("Test Name");
        patientDto.setInsurance("Test Insurance");
        Patient patient = patientService.create(patientDto);
        assertNotNull(patient);
        assertEquals(patient.getIdentification(), patientDto.getIdentification());
        assertEquals(patient.getName(), patientDto.getName());
        assertEquals(patient.getInsurance(), patientDto.getInsurance());
    }

    @Test
    @Order(2)
    void testGetByIdentification() throws Exception {
        Patient patient = patientService.getByIdentification("1053847610");
        assertEquals(patient.getIdentification(), patient.getIdentification());
    }
}

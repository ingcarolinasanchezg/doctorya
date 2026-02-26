package com.project.doctorya.tdd;

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

    @Test
    @Order(3)
    void testGetById() throws Exception{
        Patient patient = patientService.getByIdentification("1053847610");
        Patient patient2 = patientService.getById(patient.getId());
        assertEquals(patient.getIdentification(), patient2.getIdentification());
        assertEquals(patient.getId(), patient2.getId());
    }

    @Test
    @Order(4)
    void testUpdatePatient() throws Exception{
        Patient patient = patientService.getByIdentification("1053847610");
        PatientDto patientDto = new PatientDto();
        patientDto.setName("Test Name");
        Patient patientUpdate = patientService.update(patientDto, patient.getId());
        assertNotNull(patientUpdate);
        assertEquals(patientDto.getName(), patientUpdate.getName());
    }

    @Test
    @Order(5)
    void testDeletePatient() throws Exception{
        Patient patient = patientService.getByIdentification("1053847610");
        patientService.delete(patient.getId());

    }
}

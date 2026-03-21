package com.project.doctorya.bdd.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.doctorya.dtos.AppointmentDto;
import com.project.doctorya.models.Appointment;
import com.project.doctorya.models.Patient;
import com.project.doctorya.models.Doctor;
import com.project.doctorya.services.AppointmentService;
import com.project.doctorya.services.PatientService;
import com.project.doctorya.services.DoctorService;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest
public class AppointmentSteps {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    private Appointment appointment;
    private Patient patient;
    private Doctor doctor;

    // 👇 IMPORTANTE: obtener los ya creados
   @When("I create an appointment")
public void createAppointment() {

    patient = patientService.getByIdentification("123");
    doctor = doctorService.getByIdentification("456");

    AppointmentDto dto = new AppointmentDto();
    dto.setPatientId(patient.getId());
    dto.setDoctorId(doctor.getId());
    dto.setDate(java.time.LocalDateTime.now());
    dto.setReason("General check");

    appointment = appointmentService.create(dto);
}

    @Then("the appointment should be created successfully")
    public void appointmentCreatedSuccessfully() {
        assertNotNull(appointment);
    }
}
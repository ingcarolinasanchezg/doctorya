package com.project.doctorya.bdd.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.doctorya.dtos.AppointmentDto;
import com.project.doctorya.models.Appointment;
import com.project.doctorya.services.AppointmentService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest
public class AppointmentSteps {

    @Autowired
    private AppointmentService appointmentService;

    private Appointment appointment;

    private UUID appointmentId;

    @Given("the system does not have an appointment with id {string}")
    public void systemDoesNotHaveAppointment(String id) {
        try {
            appointmentService.getById(UUID.fromString(id));
        } catch (Exception e) {
            // Expected: appointment does not exist
        }
    }

    @When("I create an appointment with the following details:")
    public void createAppointment(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        Map<String, String> row = rows.get(0);

        AppointmentDto dto = new AppointmentDto();
        dto.setDoctorId(UUID.fromString(row.get("doctorId")));
        dto.setPatientId(UUID.fromString(row.get("patientId")));
        dto.setDate(LocalDateTime.parse(row.get("date")));

        appointment = appointmentService.create(dto);
        appointmentId = appointment.getId(); // 👈 importante para luego validar
    }

    @Then("the appointment should be created successfully")
    public void appointmentCreatedSuccessfully() {
        assertNotNull(appointment);
    }

    @Then("the appointment id should not be null")
    public void appointmentIdShouldNotBeNull() {
        assertNotNull(appointmentId);
    }

    @Then("the appointment doctorId should be {string}")
    public void appointmentDoctorIdShouldBe(String doctorId) {
        assertEquals(UUID.fromString(doctorId), appointment.getDoctorId());
    }

    @Then("the appointment patientId should be {string}")
    public void appointmentPatientIdShouldBe(String patientId) {
        assertEquals(UUID.fromString(patientId), appointment.getPatientId());
    }
}
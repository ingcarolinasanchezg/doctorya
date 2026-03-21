package com.project.doctorya.bdd.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.doctorya.dtos.DoctorDto;
import com.project.doctorya.models.Doctor;
import com.project.doctorya.services.DoctorService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest
public class DoctorSteps {

    @Autowired
    private DoctorService doctorService;

    private Doctor doctor;

    @Given("the system does not have a doctor with identification {string}")
    public void systemDoesNotHaveDoctor(String identification) {
        try {
            doctorService.getByIdentification(identification);
        } catch (Exception e) {
            // Expected: doctor does not exist
        }
    }

    @When("I create a doctor with the following details:")
    public void createDoctor(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        Map<String, String> row = rows.get(0);

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setIdentification(row.get("identification"));
        doctorDto.setName(row.get("name"));
        doctorDto.setSpecialty(row.get("specialty")); // 👈 igual que tu feature

        doctor = doctorService.create(doctorDto);
    }

    @Then("the doctor should be created successfully")
    public void doctorCreatedSuccessfully() {
        assertNotNull(doctor);
    }

    @Then("the doctor identification should be {string}")
    public void doctorIdentificationShouldBe(String identification) {
        assertEquals(identification, doctor.getIdentification());
    }
}
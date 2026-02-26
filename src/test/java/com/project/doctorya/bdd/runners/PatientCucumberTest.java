package com.project.doctorya.bdd.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.project.doctorya.bdd.stepdefinitions"},
    plugin = {"pretty"}
)
public class PatientCucumberTest {
}
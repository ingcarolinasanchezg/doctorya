Feature: Appointment Management
  As a clinic administrator
  I want to manage appointments
  So that I can assign patients to doctors

  Scenario: Create a new appointment
    Given the system does not have a patient with identification "123"
    And the system does not have a doctor with identification "456"
    And I create a patient with the following details:
      | identification | name   | insurance |
      | 123            | Juan   | EPS       |
    And I create a doctor with the following details:
      | identification | name     | specialty  |
      | 456            | Dr House | Cardiology |
    When I create an appointment
    Then the appointment should be created successfully
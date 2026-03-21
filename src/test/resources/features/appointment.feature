Feature: Appointment Management
  As a clinic administrator
  I want to manage appointments
  So that I can schedule consultations

  Scenario: Create a new appointment
    Given the system does not have an appointment with id "11111111-1111-1111-1111-111111111111"
    When I create an appointment with the following details:
      | doctorId                              | patientId                              | date                |
      | 11111111-1111-1111-1111-111111111111 | 22222222-2222-2222-2222-222222222222 | 2025-01-01T10:00:00 |
    Then the appointment should be created successfully
    And the appointment id should not be null
    And the appointment doctorId should be "11111111-1111-1111-1111-111111111111"
    And the appointment patientId should be "22222222-2222-2222-2222-222222222222"
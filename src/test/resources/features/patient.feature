Feature: Patient Management
  As a clinic administrator
  I want to manage patient information
  So that I can keep patient records up to date

  Scenario: Create a new patient
    Given the system does not have a patient with identification "1053847610"
    When I create a patient with the following details:
      | identification | name      | insurance      |
      | 1053847610     | Test Name | Test Insurance |
    Then the patient should be created successfully
    And the patient identification should be "1053847610"
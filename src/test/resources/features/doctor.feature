Feature: Doctor Management
  As a clinic administrator
  I want to manage doctor information
  So that I can keep doctor records up to date

 Scenario: Create a new doctor
  Given the system does not have a doctor with identification "1053123456"
  When I create a doctor with the following details:
    | identification | name      | specialty  |
    | 1053123456     | Test doc  | Cardiology |
  Then the doctor should be created successfully
  And the doctor identification should be "1053123456"
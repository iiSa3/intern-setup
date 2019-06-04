Feature: Scenarios to do with being able to call the lift

  Scenario: Before the lift has been called, the door are shut
    Given the lift has not yet been called
    Then the doors are closed
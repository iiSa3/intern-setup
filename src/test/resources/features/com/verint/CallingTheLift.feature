Feature: Scenarios to do with being able to call the lift

  Scenario: Before the lift has been called, the door are shut
    Given the lift has not yet been called
    Then the doors are closed

    Scenario: Before the lift arrives the doors are closed
      Given the lift has not yet been called
      And the lift is at floor 3
      When i call the lift from floor 2
      Then the doors are closed before it arrives



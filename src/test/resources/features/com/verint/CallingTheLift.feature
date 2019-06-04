Feature: Scenarios to do with being able to call the lift

  Scenario: Before the lift has been called, the door are shut
    Given the lift has not yet been called
    Then the doors are closed

  Scenario: Before the lift arrives the doors are closed
    Given The lift is at floor 3
    When I call the lift from floor 2
    Then the doors are closed before it arrives

  Scenario: When the lift has arrived the doors are open
    Given The lift is at floor 3
    And I call the lift from floor 2
    When The lift arrives
    Then The doors are open

  Scenario: When the lift is already on the floor the doors open
    Given The lift is at floor 3
    When I call the lift from floor 3
    Then The doors are open

  Scenario: The doors open when reaching the destination
    Given The lift is at floor 3
    And I select floor 4
    When The lift arrives
    Then The doors are open
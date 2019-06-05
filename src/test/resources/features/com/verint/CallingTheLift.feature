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
    And The lift starts to move
    When The lift arrives
    Then The doors are open

  Scenario: When the lift is already on the floor the doors open
    Given The lift is at floor 3
    When I call the lift from floor 3
    And The lift starts to move
    Then The doors are open

  Scenario: The doors open when reaching the destination
    Given The lift is at floor 3
    And I select floor 4
    And The lift starts to move
    When The lift arrives
    Then The doors are open

  Scenario: The doors open when reaching the destination
    Given The lift is at floor 3
    And I select floor 1
    And The lift starts to move
    When The lift arrives
    Then The doors are open

   Scenario: When a floor is selected the doors close
     Given The lift is at floor 2
     When I select floor 3
     Then the doors are closed

  Scenario: The lift passes floors to reach its destination
    Given The lift is at floor 1
    When I select floor 3
    And The lift starts to move
    Then the lift passes floor 2

   Scenario: Lift can only have one destination
     Given The lift is at floor 0
     And I select floor 3
     When I select floor 5
     And The lift starts to move
     Then The destination is floor 3


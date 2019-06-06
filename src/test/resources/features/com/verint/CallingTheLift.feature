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

  Scenario:  Given i select a floor then i should reach that floor
    Given The lift is at floor 6
    When I select floor 3
    And The lift starts to move
    Then The lift arrives

  Scenario: i can go on multiple trips in the lift
    Given The lift is at floor 5
    And I select floor 3
    And The lift starts to move
    And  The lift arrives
    When I select floor 5
    And The lift starts to move
    Then The lift arrives

  Scenario: invalid level followed by valid level
    Given The lift can go between floors 0 and 10
    When I select floor 11
    And The lift starts to move
    Then The lift should "not move"
    When I select floor 4
    And The lift starts to move
    Then The lift arrives

  Scenario Outline: when a floor is selectable that floor must be reachable
    Given The lift can go between floors <min> and <max>
    When I select floor <floor>
    And The lift starts to move
    Then The lift should "<action>"

    Examples:
      | min | max | floor | action   |
      | 0   | 10  | 5     | move     |
      | 0   | 10  | 11    | not move |
      | 0   | 10  | -5    | not move |
      | 0   | 10  | 0     | move     |
      | 0   | 10  | 10    | move     |
      | -2  | 10  | -2    | move     |

  Scenario: The lift can pickup passengers on the way
    Given The lift is at floor 0
    And I select floor 3
    When I call the lift from floor 2
    And The lift starts to move
    Then The lift stopped at floor 2
    And The lift starts to move
    And The lift stopped at floor 3

  Scenario: The lift can pickup passengers on the way down
    Given The lift is at floor 10
    And I select floor 3
    When I call the lift from floor 7
    And The lift starts to move
    Then The lift stopped at floor 7
    And The lift starts to move
    And The lift stopped at floor 3


  Scenario: The lift goes to all destinations
    Given The lift is at floor 3
    And I select floor 5
    And I select floor 1
    When The lift starts to move
    Then The lift stopped at floor 5
    And The lift stopped at floor 1

  Scenario: The lift goes to all (more) destinations
    Given The lift is at floor 2
    And I select floor 5
    And I select floor 1
    And I select floor 3
    And I select floor 2
    When The lift starts to move
    Then The lift stopped at floor 3
    And The lift stopped at floor 5
    And The lift stopped at floor 1

  Scenario: The lift goes only in one direction
    Given The lift is at floor 3
    And I select floor 1
    And I select floor 4
    And I select floor 9
    When The lift starts to move
    Then The lift order of arrival is "4,9,1"
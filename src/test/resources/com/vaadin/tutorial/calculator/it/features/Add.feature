Feature: Add


  Scenario: Adding to zero
    Given a calculator with the value "0"
    When the user adds
    Then the calculator value is "1"

  Scenario: Add to negative number
    Given a calculator with the value "-5"
    When the user adds
    Then the calculator value is "-4"

  Scenario: Add twice
    Given a calculator with the value "1"
    When the user adds
    And the user adds
    Then the calculator value is "3"

  Scenario: Add to positive number
    Given a calculator with the value "2"
    When the user adds
    Then the calculator value is "3"

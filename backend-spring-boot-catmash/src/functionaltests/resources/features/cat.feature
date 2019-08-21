Feature: Vote for a cat

  Scenario: Get a cats proposal
    When I request a cats proposal
    Then a cats proposal is returned

  Scenario: Vote for a cat from a proposal
    Given I have done a cats proposal
    When I vote for the first cat of the proposal
    Then a vote is returned

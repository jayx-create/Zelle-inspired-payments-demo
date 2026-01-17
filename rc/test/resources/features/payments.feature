Feature: Zelle-inspired Payments and Account Transfers
  As a user
  I want to send payments and transfer money between accounts
  So that the system processes transactions correctly and shows messages in English or Spanish

  Background:
    Given a set of users, accounts, cards, and transfers from JSON

  Scenario: First transfer
    When the system processes the first transfer
    Then the transfer result should match the expected status

  Scenario: Second transfer
    When the system processes the second transfer
    Then the transfer result should match the expected status

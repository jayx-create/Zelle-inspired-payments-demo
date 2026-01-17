Feature: Account Transfers with Multi-language Support
  As a user I want to transfer money between checking and savings accounts
  So that the system displays correct messages and scheduling options in English or Spanish

  Background:
    Given the users has a checkings and savings account
    And the cards include a Mastercard and Amex

  Scenario: Transfer from Checking to Savings today in English
    When user selects a checking account to transfer $100 to savings
    And user chooses today's date
    Then the transfer should be scheduled for same day transfer
    And the system message should be "Transfer scheduled successfully"

  Scenario: Transfer from Checking to Savings tomorrow in Spanish
    When user selects a checking account to transfer $300 to savings
    And user chooses a future date
    Then the transfer should be scheduled for different calendar date transfer
    And the system message should be "Transferencia programada con éxito"


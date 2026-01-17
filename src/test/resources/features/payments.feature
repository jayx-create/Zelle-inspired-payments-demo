srFeature: Zelle-inspired Payments and Account Transfers
  As a user
  I want to send payments and transfer money between accounts
  So that the system processes transactions correctly and shows messages in English or Spanish

  Scenario Outline: Transfer money between accounts
    Given a user with a <fromAccount> account and a <toAccount> account
    When the user transfers <amount> using <cardType>
    Then the transfer result should be "<expectedStatus>" in <language>

    Examples:
      | fromAccount | toAccount | amount | cardType   | expectedStatus | language |
      | CHECKING    | SAVINGS   | 100    | MASTERCARD | COMPLETED      | EN       |
      | CHECKING    | SAVINGS   | 5000   | AMEX       | FAILED         | ES       |

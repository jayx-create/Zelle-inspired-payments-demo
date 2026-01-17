package com.peerpay.steps;

import com.peerpay.PaymentService;
import com.peerpay.model.Account;
import com.peerpay.model.Transfer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class PaymentSteps {

    private List<Account> accounts;
    private PaymentService service;

    private String lastResult;
    private Transfer lastTransfer;

    private String fromAccount;
    private String toAccount;


    private Long activeUserId = 1L;

    @Given("a user with a {word} account and a {word} account")
    public void a_user_with_a_from_account_and_a_to_account(String fromAccount, String toAccount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;


        accounts = new ArrayList<>();
        accounts.add(new Account(1L, "CHECKING", 1000.0));
        accounts.add(new Account(1L, "SAVINGS", 500.0));
        accounts.add(new Account(2L, "CHECKING", 1500.0));
        accounts.add(new Account(2L, "SAVINGS", 800.0));

        service = new PaymentService(accounts);
    }

    @When("the user transfers {double} using {word}")
    public void the_user_transfers_amount_using_cardType(double amount, String cardType) {
      
        activeUserId = (amount >= 5000) ? 2L : 1L;

    
        lastTransfer = new Transfer(
                activeUserId,
                fromAccount,
                toAccount,
                cardType,
                amount,
                LocalDate.now(),
                null,  
                null  
        );

        lastResult = service.processTransfer(lastTransfer);
    }

    @Then("the transfer result should be {string} in {word}")
    public void the_transfer_result_should_be_expectedStatus_in_language(String expectedStatus, String language) {
        String expectedMessage;

        if ("COMPLETED".equals(expectedStatus)) {
            expectedMessage = "ES".equals(language)
                    ? "Transferencia programada con éxito"
                    : "Transfer scheduled successfully";
        } else {
            
            if ("AMEX".equals(lastTransfer.getCardType()) && lastTransfer.getAmount() >= 5000) {
                expectedMessage = "ES".equals(language)
                        ? "Transferencia excede el límite de la tarjeta"
                        : "Transfer exceeds card limit";
            } else {
                expectedMessage = "ES".equals(language)
                        ? "Fondos insuficientes"
                        : "Insufficient funds";
            }
        }

        assertEquals(lastResult, expectedMessage);
    }
}

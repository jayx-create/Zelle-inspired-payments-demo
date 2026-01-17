
package com.peerpay.steps;

import com.peerpay.PaymentService;
import com.peerpay.model.Account;
import com.peerpay.model.Transfer;
import io.cucumber.java.en.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class PaymentSteps {

    private List<Account> accounts;
    private List<Transfer> transfers;
    private PaymentService service;
    private String lastResult;

   
    @Given("a set of users, accounts, and transfers")
    public void loadData() {
        accounts = new ArrayList<>();
        accounts.add(new Account(1L, "CHECKING", 1000.0));
        accounts.add(new Account(1L, "SAVINGS", 500.0));
        accounts.add(new Account(2L, "CHECKING", 1500.0));
        accounts.add(new Account(2L, "SAVINGS", 800.0));

        transfers = new ArrayList<>();
        transfers.add(new Transfer(
                1L, "CHECKING", "SAVINGS", "MASTERCARD", 100.0,
                LocalDate.now(), "COMPLETED", "EN"
        ));
        transfers.add(new Transfer(
                2L, "CHECKING", "SAVINGS", "AMEX", 5000.0,
                LocalDate.now(), "FAILED", "ES"
        ));

        service = new PaymentService(accounts);
    }

    @When("the system processes the first transfer")
    public void processFirstTransfer() {
        lastResult = service.processTransfer(transfers.get(0));
    }

    @When("the system processes the second transfer")
    public void processSecondTransfer() {
        lastResult = service.processTransfer(transfers.get(1));
    }

    @Then("the transfer result should match the expected status")
    public void verifyTransfer() {
        Transfer t = transfers.get(0).getExpectedStatus().equals("COMPLETED") ? transfers.get(0) : transfers.get(1);

        String expectedMessage;
        if ("COMPLETED".equals(t.getExpectedStatus())) {
            expectedMessage = t.getLanguage().equals("ES") ?
                    "Transferencia programada con éxito" :
                    "Transfer scheduled successfully";
        } else {
            if ("AMEX".equals(t.getCardType()) && t.getAmount() > 5000) {
                expectedMessage = t.getLanguage().equals("ES") ?
                        "Transferencia excede el límite de la tarjeta" :
                        "Transfer exceeds card limit";
            } else {
                expectedMessage = t.getLanguage().equals("ES") ?
                        "Fondos insuficientes" :
                        "Insufficient funds";
            }
        }

        assertEquals(lastResult, expectedMessage);
    }
}

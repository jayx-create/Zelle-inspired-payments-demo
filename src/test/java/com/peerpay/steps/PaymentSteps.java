package com.peerpay.steps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peerpay.PaymentService;
import com.peerpay.model.Account;
import com.peerpay.model.Transfer;
import io.cucumber.java.en.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class PaymentSteps {

    private List<Account> accounts = new ArrayList<>();
    private List<Transfer> transfers = new ArrayList<>();
    private PaymentService service;
    private String lastResult;

    @Given("a set of users, accounts, cards, and transfers from JSON")
    public void loadData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var root = mapper.readTree(new File("src/test/resources/data/data.json"));

        var accArray = root.get("accounts");
        for (var node : accArray) {
            accounts.add(new Account(
                    node.get("userId").asLong(),
                    node.get("type").asText(),
                    node.get("balance").asDouble()
            ));
        }

        var trArray = root.get("transfers");
        for (var node : trArray) {
            transfers.add(new Transfer(
                    node.get("fromUserId").asLong(),
                    node.get("fromAccount").asText(),
                    node.get("toAccount").asText(),
                    node.get("cardType").asText(),
                    node.get("amount").asDouble(),
                    LocalDate.parse(node.get("scheduledDate").asText()),
                    node.get("expectedStatus").asText(),
                    node.get("language").asText()
            ));
        }

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
        Transfer t = transfers.stream()
                .filter(tr -> (tr.getExpectedStatus().equals("COMPLETED") && lastResult.contains("Transfer")) ||
                              tr.getExpectedStatus().equals("FAILED"))
                .findFirst().orElse(transfers.get(0));

        if ("COMPLETED".equals(t.getExpectedStatus())) {
            assertEquals(lastResult, "ES".equals(t.getLanguage()) ? "Transferencia programada con éxito" : "Transfer scheduled successfully");
        } else {
            if (t.getCardType().equals("AMEX") && t.getAmount() > 5000)
                assertEquals(lastResult, "ES".equals(t.getLanguage()) ? "Transferencia excede el límite de la tarjeta" : "Transfer exceeds card limit");
            else
                assertEquals(lastResult, "ES".equals(t.getLanguage()) ? "Fondos insuficientes" : "Insufficient funds");
        }
    }
}

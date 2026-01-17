package com.peerpay.model;

import java.time.LocalDate;

public class Transfer {

    private Long userId;
    private String fromAccount;
    private String toAccount;
    private String cardType;     
    private double amount;
    private LocalDate transferDate;
    private String expectedStatus;  
    private String language;        

    public Transfer(Long userId,
                    String fromAccount,
                    String toAccount,
                    String cardType,
                    double amount,
                    LocalDate transferDate,
                    String expectedStatus,
                    String language) {

        this.userId = userId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.cardType = cardType;
        this.amount = amount;
        this.transferDate = transferDate;
        this.expectedStatus = expectedStatus;
        this.language = language;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public String getCardType() {
        return cardType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public String getExpectedStatus() {
        return expectedStatus;
    }

    public String getLanguage() {
        return language;
    }
}

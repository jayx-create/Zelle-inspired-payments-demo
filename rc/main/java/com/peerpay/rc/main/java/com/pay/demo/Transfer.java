package com.pay.demo;

import java.time.LocalDate;

public class Transfer {
    private Long fromUserId;
    private String fromAccount;
    private String toAccount;
    private String cardType;
    private double amount;
    private LocalDate scheduledDate;
    private String expectedStatus; // COMPLETED or FAILED
    private String language; // EN or ES

    public Transfer() {}

    public Transfer(Long fromUserId, String fromAccount, String toAccount, String cardType,
                    double amount, LocalDate scheduledDate, String expectedStatus, String language) {
        this.fromUserId = fromUserId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.cardType = cardType;
        this.amount = amount;
        this.scheduledDate = scheduledDate;
        this.expectedStatus = expectedStatus;
        this.language = language;
    }

    public Long getFromUserId() { return fromUserId; }
    public String getFromAccount() { return fromAccount; }
    public String getToAccount() { return toAccount; }
    public String getCardType() { return cardType; }
    public double getAmount() { return amount; }
    public LocalDate getScheduledDate() { return scheduledDate; }
    public String getExpectedStatus() { return expectedStatus; }
    public String getLanguage() { return language; }

    public void setFromUserId(Long fromUserId) { this.fromUserId = fromUserId; }
    public void setFromAccount(String fromAccount) { this.fromAccount = fromAccount; }
    public void setToAccount(String toAccount) { this.toAccount = toAccount; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setScheduledDate(LocalDate scheduledDate) { this.scheduledDate = scheduledDate; }
    public void setExpectedStatus(String expectedStatus) { this.expectedStatus = expectedStatus; }
    public void setLanguage(String language) { this.language = language; }
}

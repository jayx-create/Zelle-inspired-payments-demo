package com.peerpay.model;

public class Payment {
    private Long fromUserId;
    private Long toUserId;
    private double amount;
    private String cardType; // MASTERCARD, AMEX

    public Payment() {}

    public Payment(Long fromUserId, Long toUserId, double amount, String cardType) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.cardType = cardType;
    }

    public Long getFromUserId() { return fromUserId; }
    public Long getToUserId() { return toUserId; }
    public double getAmount() { return amount; }
    public String getCardType() { return cardType; }

    public void setFromUserId(Long fromUserId) { this.fromUserId = fromUserId; }
    public void setToUserId(Long toUserId) { this.toUserId = toUserId; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setCa

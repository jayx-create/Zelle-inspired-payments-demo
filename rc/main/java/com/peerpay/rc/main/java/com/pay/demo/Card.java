package com.peerpay.model;

public class Card {
    private Long userId;
    private String cardType; // MASTERCARD or AMEX
    private double limit;

    public Card() {}

    public Card(Long userId, String cardType, double limit) {
        this.userId = userId;
        this.cardType = cardType;
        this.limit = limit;
    }

    public Long getUserId() { return userId; }
    public String getCardType() { return cardType; }
    public double getLimit() { return limit; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    public void setLimit(double limit) { this.limit = limit; }
}

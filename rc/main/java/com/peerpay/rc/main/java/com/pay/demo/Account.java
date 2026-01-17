package com.peerpay.model;

public class Account {
    private Long userId;
    private String type; // CHECKING or SAVINGS
    private double balance;

    public Account() {}

    public Account(Long userId, String type, double balance) {
        this.userId = userId;
        this.type = type;
        this.balance = balance;
    }

    public Long getUserId() { return userId; }
    public String getType() { return type; }
    public double getBalance() { return balance; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setType(String type) { this.type = type; }
    public void setBalance(double balance) { this.balance = balance; }
}

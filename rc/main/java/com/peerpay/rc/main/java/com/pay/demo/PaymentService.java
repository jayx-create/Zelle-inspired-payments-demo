package com.pay;

import com.peerpay.model.Account;
import com.peerpay.model.Transfer;

import java.time.LocalDate;
import java.util.List;

public class PaymentService {

    private final List<Account> accounts;

    public PaymentService(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String processTransfer(Transfer t) {
        Account fromAcc = accounts.stream()
                .filter(a -> a.getUserId().equals(t.getFromUserId()) && a.getType().equals(t.getFromAccount()))
                .findFirst().orElse(null);
        Account toAcc = accounts.stream()
                .filter(a -> a.getUserId().equals(t.getFromUserId()) && a.getType().equals(t.getToAccount()))
                .findFirst().orElse(null);

        if (fromAcc == null || toAcc == null) return localizedMessage("Account not found", t.getLanguage());

        if ((t.getCardType().equals("AMEX") && t.getAmount() > 5000) ||
            (t.getCardType().equals("MASTERCARD") && t.getAmount() > 2000))
            return localizedMessage("Transfer exceeds card limit", t.getLanguage());

        if (fromAcc.getBalance() < t.getAmount()) return localizedMessage("Insufficient funds", t.getLanguage());

        if (!t.getScheduledDate().isAfter(LocalDate.now())) {
            fromAcc.setBalance(fromAcc.getBalance() - t.getAmount());
            toAcc.setBalance(toAcc.getBalance() + t.getAmount());
            return localizedMessage("Transfer scheduled successfully", t.getLanguage());
        }

        return localizedMessage("Transfer scheduled for future date", t.getLanguage());
    }

    private String localizedMessage(String msg, String lang) {
        if ("ES".equals(lang)) {
            return switch (msg) {
                case "Transfer scheduled successfully" -> "Transferencia programada con éxito";
                case "Transfer scheduled for future date" -> "Transferencia programada para una fecha futura";
                case "Transfer exceeds card limit" -> "Transferencia excede el límite de la tarjeta";
                case "Insufficient funds" -> "Fondos insuficientes";
                case "Account not found" -> "Cuenta no encontrada";
                default -> msg;
            };
        }
        return msg;
    }
}

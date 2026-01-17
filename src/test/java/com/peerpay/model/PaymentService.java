package com.peerpay;

import com.peerpay.model.Account;
import com.peerpay.model.Transfer;

import java.util.List;

public class PaymentService {

    private List<Account> accounts;

    public PaymentService(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String processTransfer(Transfer transfer) {

        Account from = findAccount(
                transfer.getUserId(),
                transfer.getFromAccount()
        );

        if (from == null || from.getBalance() < transfer.getAmount()) {
            return message(
                    transfer.getLanguage(),
                    "Insufficient funds",
                    "Fondos insuficientes"
            );
        }

        if ("AMEX".equals(transfer.getCardType()) && transfer.getAmount() > 5000) {
            return message(
                    transfer.getLanguage(),
                    "Transfer exceeds card limit",
                    "Transferencia excede el límite de la tarjeta"
            );
        }

        from.debit(transfer.getAmount());

        return message(
                transfer.getLanguage(),
                "Transfer scheduled successfully",
                "Transferencia programada con éxito"
        );
    }

    private Account findAccount(Long userId, String type) {
        return accounts.stream()
                .filter(a -> a.getUserId().equals(userId)
                        && a.getAccountType().equals(type))
                .findFirst()
                .orElse(null);
    }

    private String message(String language, String en, String es) {
        return "ES".equals(language) ? es : en;
    }
}

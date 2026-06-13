package entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private Type type;
    private LocalDateTime dateTime;
    private String accountNumber;
    private double amount;

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    private String transactionId;

    public Transaction(Type type, String accountNumber, double amount) {
        this.type = type;
        this.dateTime = LocalDateTime.now();
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionId = UUID.randomUUID().toString();
    }
}

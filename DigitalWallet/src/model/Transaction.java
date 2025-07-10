package model;

import enums.TransactionStatus;
import enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {

    private final String transactionId;
    private final String userId;
    private final double amount;
    private final TransactionStatus transactionStatus;
    private final TransactionType transactionType;
    private final LocalDateTime timestamp;

    public Transaction(String transactionId, String userId, double amount, TransactionStatus transactionStatus, TransactionType transactionType, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", userId='" + userId + '\'' +
                ", amount=" + amount +
                ", transactionStatus=" + transactionStatus +
                ", timestamp=" + timestamp +
                '}';
    }
}

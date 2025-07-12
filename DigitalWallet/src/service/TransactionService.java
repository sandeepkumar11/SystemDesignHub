package service;

public interface TransactionService {
    String transferFunds(String fromUserId, String toUserId, double amount);

    boolean processTransaction(String userId, String transactionId, double amount);

    boolean refundTransaction(String userId, String transactionId, double amount);

    double getTransactionAmount(String userId, String transactionId);

    String getTransactionStatus(String userId, String transactionId);

    String getTransactionDetails(String userId, String transactionId);

    boolean validateTransaction(String userId, String transactionId);
}

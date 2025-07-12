package service.impl;

import enums.TransactionStatus;
import enums.TransactionType;
import exception.InsufficientBalanceException;
import exception.ItemNotExistException;
import model.Transaction;
import model.TransferTransactionDetails;
import repository.TransactionRepository;
import repository.TransferRepository;
import service.TransactionService;
import service.UserService;
import service.WalletService;
import strategy.TransactionIdGenerator;
import strategy.impl.UUIDIdGenerator;

import java.time.LocalDateTime;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository<Transaction> transactionRepository;
    private final TransferRepository transferRepository;
    private final WalletService walletService;
    private final UserService userService;
    private TransactionIdGenerator transactionIdGenerator = new UUIDIdGenerator();

    public TransactionServiceImpl(TransactionRepository<Transaction> transactionRepository,
                                  TransferRepository transferRepository,
                                  WalletService walletService,
                                  UserService userService) {
        this.transactionRepository = transactionRepository;
        this.transferRepository = transferRepository;
        this.walletService = walletService;
        this.userService = userService;
    }

    @Override
    public String transferFunds(String fromUserId, String toUserId, double amount) {

        if(fromUserId.equals(toUserId)){
            throw new IllegalArgumentException("Cannot transfer funds to the same user: " + fromUserId);
        }

        checkUserExists(fromUserId);
        checkUserExists(toUserId);

        // Check if the sender has sufficient funds
        double senderBalance = walletService.getBalance(fromUserId);
        String transactionId = transactionIdGenerator.generateId();
        if (senderBalance < amount) {
            throw new InsufficientBalanceException("Insufficient balance for user: " + fromUserId);
        }

        // Deduct amount from sender's wallet
        boolean debitSuccess = walletService.debitWallet(fromUserId, amount);
        if (!debitSuccess) {
            return transactionId;
        }

        // Credit amount to receiver's wallet
        boolean creditSuccess = walletService.creditWallet(toUserId, amount);
        if (!creditSuccess) {
            // Rollback debit if credit fails
            walletService.creditWallet(fromUserId, amount);
            return transactionId;
        }


        TransactionStatus transactionStatus = TransactionStatus.COMPLETED;
        TransactionType transactionType = TransactionType.TRANSFER;

        // Create a transaction record
        Transaction transaction = new Transaction(transactionId, fromUserId, amount,
                transactionStatus, transactionType,
                LocalDateTime.now());
        transactionRepository.save(transaction);
        TransferTransactionDetails details = new TransferTransactionDetails(transactionId, fromUserId, toUserId);
        transferRepository.save(details);

        return transactionId;
    }

    @Override
    public boolean processTransaction(String userId, String transactionId, double amount) {
        return false;
    }

    @Override
    public boolean refundTransaction(String userId, String transactionId, double amount) {
        return false;
    }

    @Override
    public double getTransactionAmount(String userId, String transactionId) {
        checkUserExists(userId);
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ItemNotExistException("Transaction not found: " + transactionId))
                .getAmount();
    }

    @Override
    public String getTransactionStatus(String userId, String transactionId) {
        checkUserExists(userId);
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ItemNotExistException("Transaction not found: " + transactionId))
                .getTransactionStatus().name();
    }

    @Override
    public String getTransactionDetails(String userId, String transactionId) {
        checkUserExists(userId);
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ItemNotExistException("Transaction not found: " + transactionId));
        Optional<TransferTransactionDetails> details = transferRepository.findById(transactionId);
        return details.map(transactionDetails -> "Transaction ID: " + transaction.getTransactionId() +
                ", User ID: " + transaction.getUserId() +
                ", Amount: " + transaction.getAmount() +
                ", Status: " + transaction.getTransactionStatus() +
                ", Type: " + transaction.getTransactionType() +
                ", Timestamp: " + transaction.getTimestamp() +
                ", Transfer Details: " + transactionDetails).orElseGet(() -> "Transaction ID: " + transaction.getTransactionId() +
                ", User ID: " + transaction.getUserId() +
                ", Amount: " + transaction.getAmount() +
                ", Status: " + transaction.getTransactionStatus() +
                ", Type: " + transaction.getTransactionType() +
                ", Timestamp: " + transaction.getTimestamp());
    }

    @Override
    public boolean validateTransaction(String userId, String transactionId) {
        return false;
    }

    public void setTransactionIdGenerator(TransactionIdGenerator transactionIdGenerator) {
        this.transactionIdGenerator = transactionIdGenerator;
    }

    private void checkUserExists(String userId) {
        if (userService.findById(userId).isEmpty()) {
            throw new ItemNotExistException("User not found: " + userId);
        }
    }

}

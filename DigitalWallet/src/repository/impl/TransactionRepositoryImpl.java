package repository.impl;

import model.Transaction;
import repository.TransactionRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionRepositoryImpl implements TransactionRepository<Transaction> {

    private final Map<String, Transaction> transactionDatabase; // transactionId -> Transaction

    public TransactionRepositoryImpl() {
        this.transactionDatabase = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Transaction transaction) {
        transactionDatabase.put(transaction.getTransactionId(), transaction);
    }

    @Override
    public Optional<Transaction> findById(String transactionId) {
        return Optional.ofNullable(transactionDatabase.get(transactionId));
    }

    @Override
    public List<Transaction> findByUserId(String userId) {
        return transactionDatabase.values().stream()
                .filter(transaction -> transaction.getUserId().equals(userId))
                .toList();
    }

    @Override
    public List<Transaction> findByUserIdAndTransactionType(String userId, String transactionType) {
        return transactionDatabase.values().stream()
                .filter(transaction -> transaction.getUserId().equals(userId)
                        && transaction.getTransactionType().toString()
                        .equals(transactionType))
                .toList();
    }

    @Override
    public void update(Transaction transaction) {
        transactionDatabase.put(transaction.getTransactionId(), transaction);
    }

    @Override
    public void deleteById(String transactionId) {
        transactionDatabase.remove(transactionId);
    }
}

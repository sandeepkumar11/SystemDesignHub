package repository.impl;

import model.TransferTransactionDetails;
import repository.TransferRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TransferRepositoryImpl implements TransferRepository {

    private final Map<String, TransferTransactionDetails> transactionStore;

    public TransferRepositoryImpl() {
        this.transactionStore = new ConcurrentHashMap<>();
    }

    @Override
    public void save(TransferTransactionDetails transactionDetails) {
        transactionStore.put(transactionDetails.getTransactionId(), transactionDetails);
    }

    @Override
    public Optional<TransferTransactionDetails> findById(String transactionId) {
        return Optional.ofNullable(transactionStore.get(transactionId));
    }

    @Override
    public void deleteById(String transactionId) {
        transactionStore.remove(transactionId);
    }
}

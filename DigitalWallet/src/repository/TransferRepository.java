package repository;

import model.TransferTransactionDetails;

import java.util.Optional;

public interface TransferRepository {
    void save(TransferTransactionDetails transactionDetails);

    Optional<TransferTransactionDetails> findById(String transactionId);

    void deleteById(String transactionId);
}

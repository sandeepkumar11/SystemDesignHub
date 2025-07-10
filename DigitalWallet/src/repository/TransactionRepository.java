package repository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository<T> {
    void save(T transferTransaction);

    Optional<T> findById(String transactionId);

    List<T> findByUserId(String userId);

    List<T> findByUserIdAndTransactionType(String userId, String transactionType);

    void update(T walletTransaction);

    void deleteById(String transactionId);
}

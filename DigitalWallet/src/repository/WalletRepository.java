package repository;

import model.Wallet;

import java.util.Optional;

public interface WalletRepository {

    Optional<Wallet> findByUserId(String userId);

    void save(Wallet wallet);

    void update(Wallet wallet);

    void deleteByUserId(String userId);
}

package repository.impl;

import model.Wallet;
import repository.WalletRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class WalletRepositoryImpl implements WalletRepository {

    private final Map<String, Wallet> walletDatabase; // userId -> Wallet mapping

    public WalletRepositoryImpl() {
        this.walletDatabase = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Wallet> findByUserId(String userId) {
        return Optional.ofNullable(walletDatabase.get(userId));
    }

    @Override
    public void save(Wallet wallet) {
        walletDatabase.put(wallet.getUserId(),wallet);
    }

    @Override
    public void update(Wallet wallet) {
        walletDatabase.put(wallet.getUserId(),wallet);
    }

    @Override
    public void deleteByUserId(String userId) {
        walletDatabase.remove(userId);
    }
}
package service;

public interface WalletService {

    void createWallet(String userId);

    boolean creditWallet(String userId, double amount);

    boolean debitWallet(String userId, double amount);

    double getBalance(String userId);
}

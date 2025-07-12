package model;

import java.util.UUID;

public class Wallet {
    private final String walletId;
    private final String userId;
    private double balance;

    public Wallet(String userId) {
        this.walletId = UUID.randomUUID().toString();
        this.userId = userId;
        this.balance = 0.0;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }

    public void addFunds(double amount) {
        this.balance += amount;
    }

    public void deductFunds(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walletId=" + walletId +
                ", userId='" + userId + '\'' +
                ", balance=" + balance +
                '}';
    }

}

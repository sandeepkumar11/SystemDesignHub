package service;

import java.util.Map;

public interface UserBalanceService {
    void addBalance(String userId, double amount);

    void addAllBalance(Map<String,Double> contributions);

    void deductBalance(String userId, double amount);

    double getBalance(String userId);
}

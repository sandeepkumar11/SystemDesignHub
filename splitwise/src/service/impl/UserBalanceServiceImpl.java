package service.impl;

import entity.UserBalance;
import exception.ItemNotFound;
import repository.UserBalanceRepository;
import service.UserBalanceService;

import java.util.Map;

public class UserBalanceServiceImpl implements UserBalanceService {
    private final UserBalanceRepository userBalanceRepository;

    public UserBalanceServiceImpl(UserBalanceRepository userBalanceRepository) {
        this.userBalanceRepository = userBalanceRepository;
    }

    @Override
    public void addBalance(String userId, double amount) {
        UserBalance userBalance = getUserBalance(userId);
        double newBalance = userBalance.getBalance() + amount;
        userBalance.setBalance(newBalance);
        userBalanceRepository.save(userBalance);
    }

    @Override
    public void addAllBalance(Map<String, Double> contributions) {
        for (Map.Entry<String, Double> entry : contributions.entrySet()) {
            addBalance(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void deductBalance(String userId, double amount) {
        UserBalance userBalance = getUserBalance(userId);
        if (userBalance.getBalance() < amount) {
            throw new ItemNotFound("Insufficient balance for user ID " + userId);
        }
        double newBalance = userBalance.getBalance() - amount;
        userBalance.setBalance(newBalance);
        userBalanceRepository.save(userBalance);
    }

    @Override
    public double getBalance(String userId) {
        return getUserBalance(userId).getBalance();
    }

    private UserBalance getUserBalance(String userId) {
        return userBalanceRepository.findByUserId(userId).orElse(new UserBalance(userId));
    }
}

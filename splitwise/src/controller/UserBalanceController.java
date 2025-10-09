package controller;

import repository.UserBalanceRepository;
import service.UserBalanceService;
import service.impl.UserBalanceServiceImpl;

public class UserBalanceController {
    private final UserBalanceService userBalanceService;

    public UserBalanceController(UserBalanceService userBalanceService) {
        this.userBalanceService = userBalanceService;
    }

    public String addBalance(String userId, double amount) {
        userBalanceService.addBalance(userId, amount);
        return "Balance added successfully for user ID " + userId;
    }

    public String getBalance(String userId) {
        double balance = userBalanceService.getBalance(userId);
        return "Current balance for user ID " + userId + " is " + balance;
    }

    public String deductBalance(String userId, double amount) {
        userBalanceService.deductBalance(userId, amount);
        return "Balance deducted successfully for user ID " + userId;
    }
}

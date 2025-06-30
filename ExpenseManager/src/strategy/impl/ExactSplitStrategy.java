package strategy.impl;

import entity.Expense;
import entity.User;
import strategy.SplitStrategy;

import java.util.List;
import java.util.Map;

public class ExactSplitStrategy implements SplitStrategy {
    private final Map<User, Double> exactAmounts;

    public ExactSplitStrategy(Map<User, Double> exactAmounts) {
        this.exactAmounts = exactAmounts;
    }

    @Override
    public Map<User,Double> split(Expense expense, List<User> participants) {

        if (exactAmounts == null || exactAmounts.isEmpty()) {
            throw new IllegalArgumentException("Exact amounts cannot be null or empty.");
        }

        for (User user : participants) {
            if (!exactAmounts.containsKey(user)) {
                throw new IllegalArgumentException("Exact amount for user " + user.getUsername() + " is not provided.");
            }
        }

        double totalAmount = 0.0;
        for (User user : participants) {
            totalAmount += exactAmounts.get(user);
        }

        if (totalAmount != expense.getAmount()) {
            throw new IllegalArgumentException("Total of exact amounts does not match the expense amount.");
        }

        return exactAmounts;
    }
}

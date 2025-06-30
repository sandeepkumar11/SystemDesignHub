package strategy.impl;

import entity.Expense;
import entity.User;
import strategy.SplitStrategy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PercentageSplitStrategy implements SplitStrategy {
    private final double[] percentages;

    public PercentageSplitStrategy(double[] percentages) {
        if (percentages == null || percentages.length == 0) {
            throw new IllegalArgumentException("Percentages array must not be null or empty");
        }
        double sum = 0;
        for (double p : percentages) {
            sum += p;
        }
        if (Math.abs(sum - 100.0) > 0.001) {
            throw new IllegalArgumentException("Percentages must sum to 100");
        }
        this.percentages = percentages;
    }

    @Override
    public Map<User,Double> split(Expense expense, List<User> participants) {
        if (participants == null || participants.size() != percentages.length) {
            throw new IllegalArgumentException("Participants list size must match percentages array size");
        }

        Map<User, Double> splitMap = new ConcurrentHashMap<>();
        for (int i = 0; i < participants.size(); i++) {
            User user = participants.get(i);
            double share = expense.getAmount() * (percentages[i] / 100.0);
            splitMap.put(user, share);
        }
        return splitMap;
    }
}

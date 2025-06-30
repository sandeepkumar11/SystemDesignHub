package strategy.impl;

import entity.Expense;
import entity.User;
import strategy.SplitStrategy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public Map<User,Double> split(Expense expense, List<User> participants) {
        double share = expense.getAmount() / participants.size();
        Map<User, Double> splitMap = new ConcurrentHashMap<>();
        for (User user : participants) {
            splitMap.put(user, share);
        }
        return splitMap;
    }
    }
}

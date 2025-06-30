package strategy;

import entity.Expense;
import entity.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    Map<User,Double> split(Expense expense, List<User>participants);
}

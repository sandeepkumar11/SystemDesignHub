package entity;

import strategy.SplitStrategy;

import java.util.List;
import java.util.Map;

public class Expense {
    private final String description;
    private final double amount;
    private final String date;
    private final User paidBy;
    private final Map<User, Double> userToAmount;
    private final SplitStrategy splitStrategy;

    public Expense(String description, double amount, String date, User paidBy, SplitStrategy splitStrategy, List<User>participants) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.paidBy = paidBy;
        this.splitStrategy = splitStrategy;
        this.userToAmount = applySplitStrategy(participants);
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public Map<User, Double> getUserToAmount() {
        return userToAmount;
    }

    public void addUserAmount(User user, double amount) {
        if (user != null && amount > 0) {
            userToAmount.merge(user, amount, Double::sum);
        }
    }

    public void removeUserAmount(User user) {
        if (user != null) {
            userToAmount.remove(user);
        }
    }

    public double getAmountForUser(User user) {
        return userToAmount.getOrDefault(user, 0.0);
    }

    public boolean isUserInvolved(User user) {
        return userToAmount.containsKey(user);
    }

    public SplitStrategy getSplitStrategy() {
        return splitStrategy;
    }

    private Map<User,Double> applySplitStrategy(List<User>participants) {
        if (splitStrategy != null && participants != null && !participants.isEmpty()) {
            return splitStrategy.split(this, participants);
        } else {
            throw new IllegalArgumentException("Split strategy or participants cannot be null or empty");
        }
    }

    @Override
    public String toString() {
        return "Expense{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", paidBy=" + paidBy.getUsername() +
                ", userToAmount=" + userToAmount +
                ", splitStrategy=" + splitStrategy.getClass().getSimpleName() +
                '}';
    }
}

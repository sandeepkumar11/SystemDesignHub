package service.impl;

import entity.Expense;
import entity.Group;
import entity.User;
import service.BalanceSheet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheetImpl implements BalanceSheet {
    private final Map<Group, Map<User, Map<User, Double>>> groupBalances;

    public BalanceSheetImpl() {
        this.groupBalances = new ConcurrentHashMap<>();
    }

    @Override
    public Map<User, Double> calculateBalances() {
        Map<User, Double> totalBalances = new ConcurrentHashMap<>();

        for (Map<User, Map<User, Double>> groupBalance : groupBalances.values()) {
            for (Map.Entry<User, Map<User, Double>> entry : groupBalance.entrySet()) {
                User user = entry.getKey();
                Map<User, Double> userToAmount = entry.getValue();

                double totalAmount = userToAmount.values().stream()
                        .mapToDouble(Double::doubleValue)
                        .sum();

                totalBalances.merge(user, totalAmount, Double::sum);
            }
        }

        return totalBalances;
    }

    @Override
    public void addExpense(Group group, Expense expense) {
        Map<User, Map<User, Double>> groupBalance = groupBalances.computeIfAbsent(
                group, k -> new ConcurrentHashMap<>());

        User paidBy = expense.getPaidBy();

        Map<User, Double> userToAmount = expense.getUserToAmount();

        for (Map.Entry<User, Double> entry : userToAmount.entrySet()) {
            User user = entry.getKey();
            double amount = entry.getValue();

            if (user.equals(paidBy)) continue;

            // user owes paidBy
            updateBalance(groupBalance, user, paidBy, amount);
        }
    }

    @Override
    public void displayBalanceSheet(Group group) {
        Map<User, Map<User, Double>> balances = groupBalances.getOrDefault(group, Map.of());

        for (User u1 : balances.keySet()) {
            for (Map.Entry<User, Double> entry : balances.get(u1).entrySet()) {
                User u2 = entry.getKey();
                double amount = entry.getValue();

                if (amount > 0) {
                    System.out.printf("%s owes %s: ₹%.2f%n", u1.getUsername(), u2.getUsername(), amount);
                }
            }
        }
    }

    private void updateBalance(Map<User, Map<User, Double>> balances,
                               User debtor, User creditor, double amount) {

        balances.computeIfAbsent(debtor, k -> new ConcurrentHashMap<>());
        balances.computeIfAbsent(creditor, k -> new ConcurrentHashMap<>());

        double debtorToCreditor = balances.get(debtor).getOrDefault(creditor, 0.0);
        double creditorToDebtor = balances.get(creditor).getOrDefault(debtor, 0.0);

        double netAmount = debtorToCreditor - creditorToDebtor + amount;

        if (netAmount >= 0) {
            balances.get(debtor).put(creditor, netAmount);
            balances.get(creditor).put(debtor, 0.0);
        } else {
            balances.get(debtor).put(creditor, 0.0);
            balances.get(creditor).put(debtor, -netAmount);
        }
    }
}

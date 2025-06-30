package service;

import entity.Expense;
import entity.Group;
import entity.User;

import java.util.Map;

public interface BalanceSheet {
    Map<User, Double> calculateBalances();

    void addExpense(Group group, Expense expense);

    void displayBalanceSheet(Group group);
}

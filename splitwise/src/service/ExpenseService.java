package service;

import dto.request.ExpenseRequest;
import dto.response.ExpenseResponse;

import java.util.List;

public interface ExpenseService {
    void addExpense(ExpenseRequest request);

    void settleExpense(String expenseId);

    ExpenseResponse getExpenseDetails(String expenseId);

    List<ExpenseResponse> getAllExpensesInGroup(String groupId);

    double getTotalExpensesForUser(String userId);

    double getUserBalance(String userId, String groupId);
}

package controller;

import dto.request.ExpenseRequest;
import dto.response.ExpenseResponse;
import repository.ExpenseRepository;
import service.ExpenseService;
import service.UserBalanceService;
import service.impl.ExpenseServiceImpl;

public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseRepository expenseRepository, UserBalanceService userBalanceService) {
        this.expenseService = new ExpenseServiceImpl(expenseRepository, userBalanceService);
    }

    public ExpenseResponse addExpense(ExpenseRequest request){
        return expenseService.addExpense(request);
    }

    public String deleteExpense(String expenseId){
        expenseService.settleExpense(expenseId);
        return "Expense with ID " + expenseId + " has been settled and removed.";
    }

    public ExpenseResponse getExpenseById(String expenseId){
        return expenseService.getExpenseDetails(expenseId);
    }
}

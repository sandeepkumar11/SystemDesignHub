package service.impl;

import dto.request.ExpenseRequest;
import dto.response.ExpenseResponse;
import entity.Expense;
import enums.ExpenseType;
import exception.ItemExists;
import exception.ItemNotFound;
import factory.ExpenseStrategyFactory;
import helper.GroupValidation;
import repository.ExpenseRepository;
import service.ExpenseService;
import service.UserBalanceService;
import strategy.ExpenseSplitStrategy;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserBalanceService userBalanceService;
    private final GroupValidation groupValidation;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserBalanceService userBalanceService) {
        this.expenseRepository = expenseRepository;
        this.userBalanceService = userBalanceService;
        this.groupValidation = new GroupValidation();

    }

    @Override
    public void addExpense(ExpenseRequest request) {
        String expenseId = generateExpenseId();
        String groupId = request.getGroupId();
        if (expenseRepository.existsById(expenseId)) {
            throw new ItemExists("Generated expense ID already exists. Try again.");
        }
        // If groupId is provided, validate it
        if (groupId != null && !groupValidation.groupExists(groupId)) {
            throw new ItemNotFound("Group with ID " + groupId + " does not exist.");
        }

        // split the expense based on the strategy
        ExpenseType expenseType = ExpenseType.fromString(request.getExpenseType());
        ExpenseSplitStrategy splitStrategy = ExpenseStrategyFactory.getExpenseStrategy(expenseType);
        splitStrategy.splitExpense(request.getAmount(),request.getParticipants());
        Map<String, Double> splits = splitStrategy.getSplitDetails();

        // Update user balances
        userBalanceService.addAllBalance(splits);

        Expense expense = new Expense(
                expenseId,
                request.getDescription(),
                request.getAmount(),
                request.getPaidBy(),
                request.getParticipants(),
                groupId
        );
        expenseRepository.save(expense);
    }

    @Override
    public void settleExpense(String expenseId) {
        if (!expenseRepository.existsById(expenseId)) {
            throw new ItemNotFound("Expense with ID " + expenseId + " does not exist.");
        }
        // For simplicity, we just delete the expense. In a real-world scenario, we might want to keep a record and update balances accordingly.
        expenseRepository.delete(expenseId);
    }

    @Override
    public ExpenseResponse getExpenseDetails(String expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() ->
                new ItemNotFound("Expense with ID " + expenseId + " does not exist."));

        return new ExpenseResponse(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getPaidBy(),
                expense.getGroupId(),
                expense.getTimestamp().toString(),
                String.join(", ", expense.getParticipants())
        );
    }

    @Override
    public List<ExpenseResponse> getAllExpensesInGroup(String groupId) {
        return List.of();
    }

    @Override
    public double getTotalExpensesForUser(String userId) {
        return userBalanceService.getBalance(userId);
    }

    @Override
    public double getUserBalance(String userId, String groupId) {
        return 0;
    }

    private String generateExpenseId() {
        return "EXP-" + UUID.randomUUID();
    }
}

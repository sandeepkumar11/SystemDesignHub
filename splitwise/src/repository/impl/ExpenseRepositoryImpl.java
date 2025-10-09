package repository.impl;

import entity.Expense;
import repository.ExpenseRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ExpenseRepositoryImpl implements ExpenseRepository {
    private final Map<String, Expense> expenseMap;

    public ExpenseRepositoryImpl() {
        this.expenseMap = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Expense expense) {
        expenseMap.put(expense.getId(), expense);
    }

    @Override
    public Optional<Expense> findById(String id) {
        return Optional.ofNullable(expenseMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return expenseMap.containsKey(id);
    }

    @Override
    public void delete(String id) {
        expenseMap.remove(id);
    }
}

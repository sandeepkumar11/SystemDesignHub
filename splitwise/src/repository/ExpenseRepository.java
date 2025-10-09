package repository;

import entity.Expense;

import java.util.Optional;

public interface ExpenseRepository {
    void save(Expense expense);

    Optional<Expense> findById(String id);

    boolean existsById(String id);

    void delete(String id);
}

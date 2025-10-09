package factory;

import enums.ExpenseType;
import strategy.ExpenseSplitStrategy;
import strategy.impl.EqualSplitStrategy;

public class ExpenseStrategyFactory {
    public static ExpenseSplitStrategy getExpenseStrategy(ExpenseType expenseType) {
        if (expenseType == null) {
            return null;
        }
        return switch (expenseType) {
            case EQUAL, EXACT, PERCENTAGE -> new EqualSplitStrategy();
            default -> null;
        };
    }
}

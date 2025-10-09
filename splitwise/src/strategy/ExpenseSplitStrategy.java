package strategy;

import java.util.List;
import java.util.Map;

public interface ExpenseSplitStrategy {
    void splitExpense(double amount, List<String> participantShares);

    Map<String, Double> getSplitDetails();
}

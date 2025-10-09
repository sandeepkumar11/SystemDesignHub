package strategy.impl;

import strategy.ExpenseSplitStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements ExpenseSplitStrategy {
    private final Map<String, Double> splitDetails;

    public EqualSplitStrategy() {
        splitDetails = new HashMap<>();
    }

    @Override
    public void splitExpense(double amount, List<String> participantShares) {
        int numParticipants = participantShares.size();
        double equalShare = amount / numParticipants;

        for (String participant : participantShares) {
            splitDetails.put(participant, equalShare);
        }
    }

    @Override
    public Map<String, Double> getSplitDetails() {
        return splitDetails;
    }
}

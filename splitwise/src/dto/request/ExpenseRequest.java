package dto.request;

import java.util.List;

public class ExpenseRequest {
    private final String description;
    private final double amount;
    private final String paidBy;
    private final String groupId;
    private final String expenseType; // EQUAL, EXACT, PERCENTAGE
    private final List<String> participants;

    public ExpenseRequest(String description, double amount, String paidBy, String groupId, String expenseType, List<String> participants) {
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.groupId = groupId;
        this.expenseType = expenseType;
        this.participants = participants;
    }

    public ExpenseRequest(String description, double amount, String paidBy, List<String> participants) {
        this(description, amount, paidBy, null,"EQUAL", participants);
    }

    public ExpenseRequest(String description, double amount, String paidBy) {
        this(description, amount, paidBy, null,"EQUAL", List.of(paidBy));
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public List<String> getParticipants() {
        return participants;
    }
}

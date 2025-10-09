package entity;

import java.time.Instant;
import java.util.List;

public class Expense {
    private final String id;
    private final String description;
    private final double amount;
    private final String paidBy;
    private final List<String> participants;
    private final Instant timestamp;
    private final String groupId;

    public Expense(String id, String description, double amount, String paidBy, List<String> participants, String groupId) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.participants = participants;
        this.timestamp = Instant.now();
        this.groupId = groupId;
    }

    public String getId() {
        return id;
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

    public List<String> getParticipants() {
        return participants;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "Expense{id='" + id + "', description='" + description
                + "', amount=" + amount + ", paidBy='" + paidBy
                + "', participants=" + participants + ", timestamp=" + timestamp
                + ", groupId='" + groupId + "'}";
    }
}

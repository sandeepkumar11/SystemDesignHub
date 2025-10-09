package dto.response;

public class ExpenseResponse {
    private final String id;
    private final String description;
    private final double amount;
    private final String paidBy;
    private final String groupId;
    private final String timestamp;
    private final String participants; // Comma-separated participant names

    public ExpenseResponse(String id, String description, double amount, String paidBy, String groupId, String timestamp, String participants) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.groupId = groupId;
        this.timestamp = timestamp;
        this.participants = participants;
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

    public String getGroupId() {
        return groupId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return "ExpenseResponse{id='" + id + "', description='" + description
                + "', amount=" + amount + ", paidBy='" + paidBy
                + "', groupId='" + groupId + "', timestamp='" + timestamp
                + "', participants='" + participants + "'}";
    }
}

package enums;

public enum TaskPriority {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private final String priority;

    TaskPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return priority;
    }
}

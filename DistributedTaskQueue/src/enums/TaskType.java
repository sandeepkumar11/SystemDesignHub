package enums;

public enum TaskType {
    SEND_EMAIL("SEND_EMAIL"),
    GENERATE_REPORT("GENERATE_REPORT"),
    PROCESS_ORDER("PROCESS_ORDER");

    private final String type;

    TaskType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

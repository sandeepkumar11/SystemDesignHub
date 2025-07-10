package enums;

public enum TransactionStatus {
    // Represents the state of a transaction
    PENDING,
    PROCESSING,
    QUEUING,
    COMPLETED,

    // Represents the final states of a transaction
    SUCCESS,
    FAILED,
    DECLINED,
    CANCELLED,
}

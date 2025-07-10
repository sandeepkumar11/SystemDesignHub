package model;

public class TransferTransactionDetails {

    private final String transactionId;
    private final String fromUserId;
    private final String toUserId;

    public TransferTransactionDetails(String transactionId, String fromUserId, String toUserId) {
        this.transactionId = transactionId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    @Override
    public String toString() {
        return "TransferTransactionDetailsEntity{" +
                "transactionId='" + transactionId + '\'' +
                ", fromUserId='" + fromUserId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                '}';
    }
}

package retry;

public class RetryResult {
    private final boolean success;
    private final String failureReason;

    public RetryResult(boolean success, String failureReason) {
        this.success = success;
        this.failureReason = failureReason;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFailureReason() {
        return failureReason;
    }
}

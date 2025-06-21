package retry;

public class RetryHandler implements RetryStrategy {
    private final int maxRetries;
    private final long delayMillis;

    public RetryHandler(int maxRetries, long delayMillis) {
        this.maxRetries = maxRetries;
        this.delayMillis = delayMillis;
    }

    @Override
    public RetryResult execute(Runnable action) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                action.run();
                return new RetryResult(true, "Action executed successfully on attempt " + (attempt + 1));
            } catch (Exception e) {
                attempt++;
                if (attempt >= maxRetries) {
                    System.err.println("Max retries reached. Action failed after " + maxRetries + " attempts.");
                    return new RetryResult(false, e.getMessage());
                }
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread was interrupted during retry delay.");
                    return new RetryResult(false, ie.getMessage());
                }
            }
        }
        return new RetryResult(false, "Action failed after " + maxRetries + " attempts.");
    }
}

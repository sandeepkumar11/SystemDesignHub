package retry;

@FunctionalInterface
public interface RetryStrategy {
    RetryResult execute(Runnable actions);
}

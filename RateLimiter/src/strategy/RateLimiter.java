package strategy;

@FunctionalInterface
public interface RateLimiter {
    boolean allowRequest(String clientId);
}

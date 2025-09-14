package strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucket implements RateLimiter {
    private final long capacity;
    private final double refillRatePerSecond;
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public TokenBucket(long capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    @Override
    public boolean allowRequest(String clientId) {
        Bucket bucket = buckets.computeIfAbsent(clientId, k -> new Bucket(capacity));
        synchronized (bucket) {
            refill(bucket);
            if (bucket.tokens >= 1) {
                bucket.tokens--;
                return true;
            }
            return false;
        }
    }

    private void refill(Bucket bucket) {
        long now = System.nanoTime();
        long elapsedNanos = now - bucket.lastRefillTimestamp;
        double tokensToAdd = (elapsedNanos / 1_000_000_000.0) / refillRatePerSecond;
        bucket.tokens = Math.min(bucket.tokens + (long) tokensToAdd, capacity);
        bucket.lastRefillTimestamp = now;
    }

    private static class Bucket {
        private long tokens;
        private long lastRefillTimestamp;

        Bucket(long tokens) {
            this.tokens = tokens;
            this.lastRefillTimestamp = System.nanoTime();
        }
    }
}

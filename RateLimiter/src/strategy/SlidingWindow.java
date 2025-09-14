package strategy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindow implements RateLimiter {
    private final long windowSizeNanos;
    private final long maxRequests;
    private final Map<String, Deque<Long>> windows = new ConcurrentHashMap<>();

    public SlidingWindow(long windowSizeSeconds, long maxRequests) {
        this.windowSizeNanos = windowSizeSeconds * 1_000_000_000L;
        this.maxRequests = maxRequests;
    }

    @Override
    public boolean allowRequest(String clientId) {
        Deque<Long> window = windows.computeIfAbsent(clientId, k -> new ArrayDeque<>());
        synchronized (window) {
            long now = System.nanoTime();

            while (!window.isEmpty() && now - window.peek() > windowSizeNanos) {
                window.poll();
            }

            if (window.size() < maxRequests) {
                window.offer(now);
                return true;
            }
            return false;
        }
    }
}

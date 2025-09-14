package strategy;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class LeakyBucket implements RateLimiter {
    private final long capacity;
    private final double leakRatePerSecond;
    private final long leakIntervalNanos;
    private final Map<String, Queue<Long>> queues = new ConcurrentHashMap<>();

    public LeakyBucket(long capacity, long leakRatePerSecond) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        if (leakRatePerSecond <= 0) {
            throw new IllegalArgumentException("Leak rate must be positive");
        }
        this.capacity = capacity;
        this.leakRatePerSecond = leakRatePerSecond;
        this.leakIntervalNanos = (long) (1_000_000_000.0 / leakRatePerSecond);
    }

    @Override
    public boolean allowRequest(String clientId) {
        Queue<Long> queue = queues.computeIfAbsent(clientId, k -> new LinkedList<>());
        synchronized (queue) {
            long now = System.nanoTime();
            while (!queue.isEmpty() && (now - queue.peek()) >= leakIntervalNanos) {
                queue.poll();
            }
            if (queue.size() < capacity) {
                queue.offer(now);
                return true;
            }
            return false;
        }
    }

    public void cleanup() {
        long now = System.nanoTime();
        queues.entrySet().removeIf(entry -> {
            Queue<Long> queue = entry.getValue();
            synchronized (queue) {
                while (!queue.isEmpty() && (now - queue.peek()) >= leakIntervalNanos) {
                    queue.poll();
                }
                return queue.isEmpty();
            }
        });
    }
}

import strategy.RateLimiter;

public class RateLimiterContext {
    private final RateLimiter rateLimiter;

    public RateLimiterContext(RateLimiter rateLimiter){
        this.rateLimiter = rateLimiter;
    }

    public boolean allowRequest(String clientId){
        return rateLimiter.allowRequest(clientId);
    }
}

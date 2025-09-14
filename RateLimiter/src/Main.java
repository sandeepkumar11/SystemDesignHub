import strategy.RateLimiter;
import strategy.TokenBucket;

public class Main {
    public static void main(String[] args) {
        RateLimiter rateLimiter = new TokenBucket(10, 1.0);
        RateLimiterContext limiterContext = new RateLimiterContext(rateLimiter);

        for(int i=0;i<15;i++){
            System.out.println("Request "+ i + ": "+ limiterContext.allowRequest("client1").getResponse());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace(System.out);
            }
        }
    }
}
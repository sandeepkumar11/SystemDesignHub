import dto.Response;
import strategy.RateLimiter;

public class RateLimiterContext {
    private final RateLimiter rateLimiter;

    public RateLimiterContext(RateLimiter rateLimiter){
        this.rateLimiter = rateLimiter;
    }

    public Response allowRequest(String clientId){
         if(rateLimiter.allowRequest(clientId)){
             return new Response( "Accepted");
         }
         return new Response("HTTP 429 Too Many Requests");
    }
}

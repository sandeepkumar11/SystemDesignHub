package provides;

public interface Provider {
    void send(String recipient, String subject, String content);
}

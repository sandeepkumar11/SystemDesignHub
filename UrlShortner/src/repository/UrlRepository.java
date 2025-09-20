package repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UrlRepository {
    private final Map<String, String> urlStore = new ConcurrentHashMap<>(); // long -> short

    public boolean hasUrl(String longUrl) {
        return urlStore.containsKey(longUrl);
    }

    public void save(String longUrl, String shortUrl) {
        if (urlStore.containsKey(longUrl)) {
            return;
        }
        urlStore.put(longUrl, shortUrl);
    }

    public Optional<String> findByShortUrl(String shortUrl) {
        for (Map.Entry<String, String> entry : urlStore.entrySet()) {
            if (entry.getValue().equals(shortUrl)) {
                return Optional.ofNullable(entry.getKey());
            }
        }
        return Optional.empty();
    }

    public Optional<String> findByLongUrl(String longUrl) {
        return Optional.ofNullable(urlStore.get(longUrl));
    }
}

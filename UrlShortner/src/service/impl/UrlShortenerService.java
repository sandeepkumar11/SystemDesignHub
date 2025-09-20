package service.impl;

import repository.UrlRepository;
import service.UrlProcessor;

import java.util.Random;
import java.util.logging.Logger;

public class UrlShortenerService implements UrlProcessor {

    private static final Logger logger = Logger.getLogger(UrlShortenerService.class.getName());
    private final UrlRepository repository;

    public UrlShortenerService(UrlRepository repository) {
        this.repository = repository;
    }

    @Override
    public String process(String url) {
        if (repository.hasUrl(url)) {
            logger.info("[debug] " + url + " exists.");
            return repository.findByLongUrl(url)
                    .orElseThrow(() -> new RuntimeException("Short URL not found for: " + url));
        }
        String shortUrl = createUrl(url);
        repository.save(url, shortUrl);
        return shortUrl;
    }

    private String createUrl(String url) {
        String base = "https://short.ly/";
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return base + sb;
    }

}

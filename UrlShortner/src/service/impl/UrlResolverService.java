package service.impl;

import repository.UrlRepository;
import service.UrlProcessor;

public class UrlResolverService implements UrlProcessor {
    private final UrlRepository repository;

    public UrlResolverService(UrlRepository repository) {
        this.repository = repository;
    }

    @Override
    public String process(String url) {
        return repository.findByShortUrl(url).orElseThrow(
                () -> new RuntimeException("No url exists to map with this url"));
    }
}

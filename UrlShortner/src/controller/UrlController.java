package controller;

import repository.UrlRepository;
import service.impl.UrlResolverService;
import service.impl.UrlShortenerService;

public class UrlController {
    private final UrlRepository repository = new UrlRepository(); // It should be injected in the service layer

    private final UrlShortenerService shortenService = new UrlShortenerService(repository);
    private final UrlResolverService unpackUrlService = new UrlResolverService(repository);

    public String getLongUrl(String url) { // get the long URL
        return unpackUrlService.process(url);
    }

    public String getShortURL(String url) { // return the short URL
        return shortenService.process(url);
    }

}

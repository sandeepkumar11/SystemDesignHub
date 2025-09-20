# URL Shortener Module

A simple Java-based URL Shortener service. It generates short URLs for given long URLs and stores the mapping in a thread-safe repository.

## Features

- Generate short URLs for long URLs
- Retrieve original URLs using short URLs
- Thread-safe in-memory storage using `ConcurrentHashMap`
- Simple logging using Java's built-in logger

## Structure

- `repository/UrlRepository.java`: Handles storage and retrieval of URL mappings.
- `service/UrlProcessor.java`: Interface for URL processing.
- `service/impl/UrlShortenerService.java`: Main service for URL shortening logic.

## Usage

1. Instantiate `UrlRepository`.
2. Create `UrlShortenerService` with the repository.
3. Call `process(String url)` to get a short URL.

## Example

```java
UrlRepository repository = new UrlRepository();
UrlShortenerService service = new UrlShortenerService(repository);

String shortUrl = service.process("https://example.com/long-url");

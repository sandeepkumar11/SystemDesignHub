# Rate Limiter Design

## Overview
This project involves designing and implementing a rate limiter for a machine coding round interview. A rate limiter restricts the number of requests a client can make to a system within a specified time window, ensuring fair usage and protecting the system from overload or abuse.

## Requirements

### Functional Requirements
1. **Limit Requests**: The rate limiter should restrict the number of requests a client (identified by a unique key, e.g., user ID or IP address) can make within a given time window (e.g., 10 requests per minute).
2. **Configurable Limits**: Allow configuration of the maximum number of requests and the time window duration (e.g., X requests per Y seconds).
3. **Client Identification**: Support rate limiting based on a unique client identifier (e.g., user ID, API key, or IP address).
4. **Thread Safety**: The rate limiter should be thread-safe to handle concurrent requests in a multi-threaded environment.
5. **Response Handling**: If a request exceeds the rate limit, return an appropriate response (e.g., HTTP 429 Too Many Requests) or throw an exception indicating the limit has been reached.
6. **Reset Mechanism**: Automatically reset the request count for a client when the time window expires.

### Non-Functional Requirements
1. **Performance**: The rate limiter should have low latency and minimal overhead to avoid impacting system performance.
2. **Scalability**: The design should be scalable to handle a large number of clients and requests.
3. **Memory Efficiency**: Optimize memory usage, especially when tracking rate limits for many clients.
4. **Extensibility**: The design should allow for easy extension to support additional features, such as dynamic rate limits or distributed rate limiting.
5. **Reliability**: Ensure accurate tracking of requests and time windows, even under high load or concurrent access.

## Constraints
1. The rate limiter should handle at least 1000 unique clients concurrently.
2. The time window can range from 1 second to 1 hour.
3. The maximum number of requests per time window can range from 1 to 1000.
4. The implementation should be in-memory for simplicity, but the design should consider future extensions for distributed systems (e.g., using Redis).

## Assumptions
1. The system clock is reliable and synchronized across threads or instances.
2. The client identifier is provided with each request.
3. The rate limiter will be tested in a single-machine environment for the coding round, but the design should be discussed for distributed scenarios.
4. No external dependencies (e.g., databases) are required for the core implementation unless specified.

## Example Use Case
- A client with ID "user123" is allowed 10 requests per 60 seconds.
- If "user123" sends 11 requests within 60 seconds, the 11th request is rejected with an appropriate error.
- After 60 seconds, the counter for "user123" resets, allowing 10 new requests.

## Implementation Considerations
1. **Algorithm Choice**: Consider algorithms like Token Bucket, Leaky Bucket, or Sliding Window for rate limiting.
2. **Data Structure**: Use efficient data structures (e.g., HashMap for client tracking, queues for sliding window) to store request counts and timestamps.
3. **Concurrency**: Use synchronization mechanisms (e.g., locks, concurrent data structures) to ensure thread safety.
4. **Edge Cases**:
    - Handle requests arriving at the boundary of a time window.
    - Manage clients with no prior requests.
    - Account for clock skew or time synchronization issues in a distributed setup.

## Deliverables
1. **Code**: A working implementation of the rate limiter in a programming language of choice (e.g., Python, Java, C++).
2. **Documentation**: Inline comments explaining the logic and design choices.
3. **Tests**: Unit tests covering key scenarios, including edge cases and concurrent access.
4. **Discussion**: Be prepared to discuss trade-offs, scalability, and potential improvements (e.g., distributed rate limiting).

## Evaluation Criteria
1. **Correctness**: The rate limiter accurately enforces the configured limits.
2. **Code Quality**: Clean, modular, and well-documented code.
3. **Performance**: Efficient handling of requests with minimal latency.
4. **Concurrency**: Proper handling of concurrent requests without race conditions.
5. **Design**: Clear explanation of the chosen algorithm and data structures.
6. **Extensibility**: Ability to extend the solution for additional features or distributed environments.
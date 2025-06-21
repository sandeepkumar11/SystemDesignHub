# Low-Level Design (LLD) Question: publisher.Message Queueing System

## Problem Statement
Design a message queueing system with the following functional and non-functional requirements. The system should handle messages efficiently, support dynamic subscriber management, enforce dependency relationships, and include a robust retry mechanism.

## Functional Requirements
1. **Custom queue.Queue Implementation**:
    - Implement a custom queue to store messages in JSON format.
    - Standard library queue implementations (e.g., Java's `queue.Queue`, Python's `queue`, etc.) are not allowed.
2. **Single publisher.Publisher**:
    - The system has one publisher that generates and sends JSON messages to the queue.
3. **Multiple Subscribers**:
    - Multiple subscribers can listen to messages that match a specific regex pattern.
    - Subscribers are loosely coupled and can be added or removed dynamically at runtime.
    - Each subscriber registers a callback function upon subscription, which is invoked when a matching message arrives.
4. **subscriber.Subscriber Dependencies**:
    - Subscribers may have many-to-many dependency relationships.
    - If subscriber A depends on subscriber B, B must process the message before A can process it.
    - Ensure dependency resolution to avoid deadlocks or cyclic dependencies.
5. **retry.RetryStrategy Mechanism**:
    - Implement a retry mechanism for handling errors (e.g., exceptions during message processing).
    - Messages that fail processing should be retried a configurable number of times before being discarded or sent to a dead-letter queue.

## Non-Functional Requirements
1. **Scalability**:
    - The system should handle a high volume of messages efficiently.
    - Support dynamic addition/removal of subscribers without performance degradation.
2. **Reliability**:
    - Ensure no message loss under normal operation.
    - Handle failures gracefully with retries and logging.
3. **Extensibility**:
    - The design should allow easy addition of new features, such as new message formats or subscriber types.
4. **Thread Safety**:
    - Ensure the system is thread-safe for concurrent message publishing and processing.

## Input/Output Expectations
- **Input**:
    - publisher.Publisher sends JSON messages (e.g., `{"id": 1, "type": "order", "data": {...}}`).
    - Subscribers register with a regex pattern (e.g., `type=order.*`) and a callback function.
    - Dependency relationships between subscribers (e.g., subscriber.Subscriber A depends on subscriber.Subscriber B).
- **Output**:
    - Subscribers receive and process messages matching their regex pattern.
    - Callback functions are invoked in the correct order based on dependencies.
    - Failed messages are retried or moved to a dead-letter queue.

## Constraints
- Messages are JSON strings with a maximum size of 1 MB.
- The system should support at least 100 subscribers.
- retry.RetryStrategy mechanism should allow at least 3 retries with configurable delays.
- Dependency graph must not have cycles.
- Assume the system runs in a single process, but multiple threads may be used for concurrency.

## Deliverables
Provide a detailed low-level design for the message queueing system, including:
1. **Class Diagram**:
    - Show the main components (e.g., queue.Queue, publisher.Publisher, subscriber.Subscriber, Dependency Manager, retry.RetryStrategy Handler).
2. **Key Data Structures and Algorithms**:
    - Describe the custom queue implementation (e.g., using a linked list or array-based structure).
    - Explain how regex matching is performed for subscribers.
    - Detail the algorithm for resolving subscriber dependencies (e.g., topological sort).
3. **Code Structure**:
    - Provide pseudocode or code snippets for critical components (e.g., queue operations, message dispatching, retry logic).
    - Highlight thread-safety mechanisms (e.g., locks, semaphores).
4. **Error Handling and retry.RetryStrategy**:
    - Explain how errors are detected and retried.
    - Describe the dead-letter queue mechanism for failed messages.
5. **Threading Model**:
    - Outline how concurrency is handled for publishing and subscriber processing.
6. **Assumptions and Trade-offs**:
    - List any assumptions made during the design.
    - Discuss trade-offs (e.g., performance vs. simplicity, memory vs. speed).

## Example Scenario
- **publisher.Publisher** sends: `{"id": 1, "type": "order_created", "data": {"user": "john", "amount": 100}}`.
- **subscriber.Subscriber A** (regex: `type=order.*`, depends on subscriber.Subscriber B):
    - Callback: Logs the order and updates a database.
- **subscriber.Subscriber B** (regex: `type=order_created`):
    - Callback: Sends a confirmation email.
- **subscriber.Subscriber C** (regex: `type=order.*`, no dependencies):
    - Callback: Updates analytics.
- If subscriber.Subscriber A’s callback throws an exception, the system retries processing up to 3 times before moving the message to a dead-letter queue.

## Evaluation Criteria
- Correctness of the custom queue implementation.
- Handling of subscriber dependencies and dynamic registration/removal.
- Robustness of the retry mechanism.
- Thread safety and concurrency handling.
- Clarity and completeness of the class diagram and pseudocode.
- Scalability and extensibility of the design.

## Additional Notes
- Consider edge cases, such as:
    - Empty queue scenarios.
    - Subscribers with invalid regex patterns.
    - Cyclic dependencies among subscribers.
    - High message throughput causing contention.
- Provide a brief explanation of how the system can be tested (e.g., unit tests for queue operations, integration tests for message flow).
# Distributed Task Queue System

This project implements a lightweight **Distributed Task Queue System** designed for a machine coding round, tailored for an e-commerce platform to handle asynchronous tasks such as sending emails, generating reports, and processing orders. The system is simple, functional, and optimized for implementation within a short timeframe, making it suitable for a Java-based interview coding round.

## Problem Statement
The goal is to design a distributed task queue system that:
- Allows tasks to be submitted to a queue for asynchronous processing.
- Supports multiple worker nodes to process tasks concurrently.
- Ensures tasks are processed reliably (no task loss).
- Handles task prioritization (e.g., high-priority tasks like order processing are executed before low-priority tasks like report generation).
- Provides fault tolerance (e.g., tasks are not lost if a worker crashes).
- Supports scalability to add more workers dynamically.
- Includes basic monitoring and logging for task status.

## Requirements
Below are the detailed requirements for implementing the Distributed Task Queue System in Java:

### Functional Requirements
1. **Task Submission**:
    - Users (e.g., e-commerce platform components) can submit tasks to the queue.
    - Each task has:
        - A unique task ID.
        - A type (e.g., `SEND_EMAIL`, `GENERATE_REPORT`, `PROCESS_ORDER`).
        - A priority level (e.g., `HIGH`, `MEDIUM`, `LOW`).
        - A payload (e.g., JSON string or serialized object containing task-specific data).
    - Tasks are enqueued in a centralized queue or distributed across multiple queues.

2. **Task Processing**:
    - Multiple worker nodes (threads or processes) dequeue and process tasks concurrently.
    - Tasks are processed based on priority (higher-priority tasks are processed first).
    - Workers execute tasks by invoking a task-specific handler (e.g., a method to send an email or process an order).

3. **Reliability**:
    - Tasks are not lost even if a worker crashes during processing.
    - Tasks that fail are retried a configurable number of times before being marked as failed.
    - Failed tasks are logged or moved to a dead-letter queue for manual inspection.

4. **Scalability**:
    - The system supports adding more worker nodes dynamically to handle increased load.
    - The task queue can handle a large number of tasks without significant performance degradation.

5. **Monitoring and Logging**:
    - Track task status (e.g., `PENDING`, `IN_PROGRESS`, `COMPLETED`, `FAILED`).
    - Log task execution details (e.g., start time, end time, worker ID, and failure reasons).
    - Provide a basic API or interface to query task status.

### Non-Functional Requirements
1. **Performance**:
    - The system should handle at least 100 tasks per minute with 2-3 worker nodes.
    - Task enqueue and dequeue operations should have low latency (e.g., < 10ms per operation under normal load).

2. **Fault Tolerance**:
    - Implement basic mechanisms to handle worker crashes (e.g., reassign tasks to other workers).
    - Ensure no single point of failure in the queue management (e.g., use a distributed queue or replication).

3. **Simplicity**:
    - The codebase should be simple and modular, suitable for implementation in a 60-90 minute coding round.
    - Avoid over-engineering; focus on core functionality while keeping extensibility in mind.

4. **Thread Safety**:
    - Ensure thread-safe operations for task enqueueing, dequeuing, and status updates in a multi-threaded environment.

### Technical Requirements
1. **Programming Language**: Java (version 8 or higher).
2. **Core Libraries/Frameworks**:
    - Use standard Java libraries (e.g., `java.util.concurrent` for thread pools, queues, and synchronization).
    - Optionally, use lightweight libraries like Guava for collections or Gson for JSON parsing (if allowed in the coding round).
    - Avoid heavy frameworks like Spring or external databases unless explicitly permitted.

3. **Data Structures**:
    - Use a `PriorityBlockingQueue` or similar for prioritizing tasks.
    - Use a thread-safe data structure (e.g., `ConcurrentHashMap`) for tracking task status.
    - Optionally, use an in-memory store (e.g., a list or map) to simulate a distributed queue for simplicity.

4. **Concurrency**:
    - Use Java's `ExecutorService` for managing worker threads.
    - Ensure proper synchronization for shared resources (e.g., queue or task status map).

5. **Task Model**:
    - Define a `Task` class with fields for ID, type, priority, payload, status, and retry count.
    - Implement a `TaskHandler` interface for processing different task types.

6. **Fault Tolerance**:
    - Implement a retry mechanism for failed tasks.
    - Use a simple acknowledgment mechanism (e.g., workers acknowledge task completion to remove tasks from the queue).
    - Simulate a dead-letter queue using a separate collection for failed tasks.

7. **Monitoring**:
    - Implement a `TaskMonitor` class to track and report task statuses.
    - Log task execution details using `java.util.logging` or `System.out.println` for simplicity.

### Assumptions
- The system runs in a single JVM for the coding round, simulating a distributed environment using threads.
- No external database or message broker (e.g., Redis, RabbitMQ) is required unless specified.
- Tasks are processed in-memory for simplicity, with no persistence to disk.
- The coding round focuses on core logic (queue management, task processing, and concurrency) rather than advanced distributed system features like network communication.

### Deliverables
1. **Source Code**:
    - A complete Java program with classes for:
        - `Task`: Represents a task with ID, type, priority, payload, and status.
        - `TaskQueue`: Manages task enqueueing and dequeuing with priority support.
        - `Worker`: Processes tasks from the queue.
        - `TaskHandler`: Handles specific task types (e.g., mock implementations for email, report, or order processing).
        - `TaskMonitor`: Tracks task status and logs execution details.
    - Thread-safe implementation using appropriate concurrency utilities.

2. **Test Cases**:
    - Basic unit tests to verify:
        - Task submission and prioritization.
        - Concurrent task processing by multiple workers.
        - Retry mechanism for failed tasks.
        - Task status tracking and logging.

3. **Documentation**:
    - A brief README (this file) explaining the system design, assumptions, and how to run the code.
    - Inline code comments for clarity.

### How to Run
1. **Prerequisites**:
    - Java Development Kit (JDK) 8 or higher.
    - A Java IDE (e.g., IntelliJ IDEA, Eclipse) or a simple text editor with `javac` for compilation.
    - No external dependencies are required unless explicitly allowed.

2. **Steps**:
    - Clone or copy the project source code.
    - Compile the Java files using `javac *.java`.
    - Run the main class (e.g., `java Main`) to start the task queue system.
    - Submit sample tasks via a provided API or main method to test the system.
    - Monitor task execution via console logs or the `TaskMonitor` interface.

### Evaluation Criteria
The solution will be evaluated based on:
- **Correctness**: The system correctly handles task submission, prioritization, and processing.
- **Concurrency**: Thread-safe implementation with proper synchronization.
- **Code Quality**: Clean, modular, and well-documented code.
- **Completeness**: All core requirements (task queue, workers, retry mechanism, monitoring) are implemented.
- **Simplicity**: The solution is straightforward and suitable for a time-constrained coding round.

### Example Usage
```java
TaskQueue queue = new TaskQueue();
TaskMonitor monitor = new TaskMonitor();
ExecutorService executor = Executors.newFixedThreadPool(3); // 3 workers

// Submit tasks
queue.enqueue(new Task("1", TaskType.PROCESS_ORDER, Priority.HIGH, "{orderId: 1001}"));
queue.enqueue(new Task("2", TaskType.SEND_EMAIL, Priority.MEDIUM, "{email: user@example.com}"));

// Start workers
for (int i = 0; i < 3; i++) {
    executor.submit(new Worker(queue, new TaskHandlerImpl(), monitor));
}

// Monitor task status
monitor.printStatus();
```

### Notes for Interview Preparation
- **Focus Areas**:
    - Demonstrate proficiency in Java concurrency (e.g., `ExecutorService`, `BlockingQueue`, synchronization).
    - Show understanding of task prioritization and queue management.
    - Highlight fault tolerance and retry mechanisms.
    - Keep the code modular and extensible for potential follow-up questions (e.g., adding persistence or distributed nodes).
- **Common Follow-Up Questions**:
    - How would you scale this system to a truly distributed environment (e.g., using Kafka or Redis)?
    - How would you handle task dependencies (e.g., task B depends on task A)?
    - How would you improve fault tolerance with a persistent queue?
    - How would you optimize for high-throughput task processing?

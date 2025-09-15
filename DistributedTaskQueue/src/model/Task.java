package model;

import enums.TaskPriority;
import enums.TaskStatus;
import enums.TaskType;
import java.time.Instant;

public class Task {
    private final String id;
    private final TaskType type;
    private TaskPriority priority;
    private TaskStatus status;
    private final String payload;
    private final Instant createdAt;
    private Instant updatedAt;

    public Task(String id, TaskType type, String payload) {
        this.id = id;
        this.type = type;
        this.priority = TaskPriority.MEDIUM;
        this.status = TaskStatus.PENDING;
        this.payload = payload;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public String getId() { return id; }
    public TaskType getType() { return type; }
    public TaskPriority getPriority() { return priority; }
    public TaskStatus getStatus() { return status; }
    public String getPayload() { return payload; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
        this.updatedAt = Instant.now();
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
        this.updatedAt = Instant.now();
    }

    public boolean isProcessable() {
        return status == TaskStatus.PENDING || status == TaskStatus.RETRY;
    }
}
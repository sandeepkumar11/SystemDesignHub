package producer;

import helper.TaskStore;
import model.Task;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskProducer implements Producer {
    private final TaskStore taskStore;
    private final ExecutorService executor;
    private final AtomicBoolean isRunning;

    public TaskProducer(TaskStore taskStore) {
        this.taskStore = taskStore;
        executor = Executors.newCachedThreadPool();
        isRunning = new AtomicBoolean(true);
    }

    @Override
    public CompletableFuture<String> produce(Task task, String topic) {
        if (!isRunning.get()) {
            return CompletableFuture.failedFuture(new IllegalStateException("Producer is closed"));
        }
        if (task == null || topic == null || topic.isEmpty()) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("Task or Topic can't be null"));
        }
        return CompletableFuture.supplyAsync(() -> {
            taskStore.addTask(topic, task);
            return UUID.randomUUID().toString();
        }, executor);
    }

    @Override
    public CompletableFuture<Void> produceBatch(List<Task> tasks, String topic) {
        if (!isRunning.get()) {
            return CompletableFuture.failedFuture(new IllegalStateException("Producer is closed"));
        }
        if (tasks == null || topic == null || topic.isEmpty()) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("Task or Topic can't be null"));
        }
        return CompletableFuture.runAsync(() -> taskStore.addTasks(topic, tasks), executor);
    }

    @Override
    public boolean topicExists(String topic) {
        return taskStore.topicExists(topic);
    }

    @Override
    public void close() {
        isRunning.set(false);
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void flush() {

    }
}

package helper;

import enums.TaskPriority;
import model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class InMemoryTaskStore implements TaskStore {
    private final ConcurrentHashMap<String, PriorityBlockingQueue<Task>> topicStore;
    private final AtomicBoolean isRunning;

    public InMemoryTaskStore() {
        topicStore = new ConcurrentHashMap<>();
        isRunning = new AtomicBoolean(true);
    }

    @Override
    public void addTask(String topic, Task task) {
        if (!isRunning.get()) {
            throw new IllegalStateException("Task store is closed");
        }

        topicStore.computeIfAbsent(topic, k -> new PriorityBlockingQueue<>(10,
                Comparator.comparing(Task::getPriority, Comparator.reverseOrder()))).offer(task);
    }

    @Override
    public void addTasks(String topic, List<Task> tasks) {
        if (!isRunning.get()) {
            throw new IllegalStateException("Task store is closed");
        }

        topicStore.computeIfAbsent(topic, k -> new PriorityBlockingQueue<>(10,
                Comparator.comparing(Task::getPriority, Comparator.reverseOrder()))).addAll(tasks);
    }

    @Override
    public boolean topicExists(String topic) {
        return topicStore.containsKey(topic);
    }

    @Override
    public List<Task> pollTasks(String topic, long timeout, int maxTasks) throws InterruptedException {
        if (!isRunning.get()) {
            throw new IllegalStateException("Task store is closed");
        }
        PriorityBlockingQueue<Task> queue = topicStore.getOrDefault(topic, null);
        if (queue == null) {
            return Collections.emptyList();
        }
        List<Task> tasks = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        while (tasks.size() < maxTasks && System.currentTimeMillis() - startTime < timeout) {
            Task task = queue.poll(timeout, TimeUnit.MILLISECONDS);
            if (task != null && task.isProcessable()) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    @Override
    public void createTopic(String topic) {
        if (!isRunning.get()) {
            throw new IllegalStateException("Task store is closed");
        }
        topicStore.computeIfAbsent(topic, k -> new PriorityBlockingQueue<>(10,
                Comparator.comparing(Task::getPriority, Comparator.reverseOrder())));
    }

    @Override
    public void shutdown() {
        isRunning.set(false);
        topicStore.clear();
    }
}

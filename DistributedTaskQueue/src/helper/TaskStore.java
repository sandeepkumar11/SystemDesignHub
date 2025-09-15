package helper;

import model.Task;

import java.util.List;

public interface TaskStore {
    void addTask(String topic, Task task);

    void addTasks(String topic, List<Task> tasks);

    boolean topicExists(String topic);

    List<Task> pollTasks(String topic, long timeout, int maxTasks) throws InterruptedException;

    void createTopic(String topic);

    void shutdown();
}

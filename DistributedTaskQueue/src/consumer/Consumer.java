package consumer;

import model.Task;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Consumer {
    void subscribe(String topic);

    void unsubscribe(String topic);

    CompletableFuture<List<Task>> poll(String topic, long timeout, int maxTasks);

    void close();
}

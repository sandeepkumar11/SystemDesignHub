package producer;

import model.Task;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Producer {
    CompletableFuture<String> produce(Task task, String topic);

    CompletableFuture<Void> produceBatch(List<Task> tasks, String topic);

    boolean topicExists(String topic);

    void close();
    void flush();
}

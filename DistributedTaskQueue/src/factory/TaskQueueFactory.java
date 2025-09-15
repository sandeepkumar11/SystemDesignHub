package factory;

import consumer.Consumer;
import consumer.TaskConsumer;
import helper.TaskStore;
import producer.Producer;
import producer.TaskProducer;

public class TaskQueueFactory {
    private final TaskStore taskStore;

    public TaskQueueFactory(TaskStore taskStore) {
        this.taskStore = taskStore;
    }

    public Producer createProducer() {
        return new TaskProducer(taskStore);
    }

    public Consumer createConsumer() {
        return new TaskConsumer(taskStore);
    }

    public void shutdown() {
        taskStore.shutdown();
    }
}

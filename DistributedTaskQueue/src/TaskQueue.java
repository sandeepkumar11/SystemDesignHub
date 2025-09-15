import consumer.Consumer;
import enums.TaskPriority;
import enums.TaskType;
import factory.TaskQueueFactory;
import helper.InMemoryTaskStore;
import helper.TaskStore;
import model.Task;
import producer.Producer;

import java.util.concurrent.CompletableFuture;

public class TaskQueue {
    public void demo() {
        TaskStore taskStore = new InMemoryTaskStore();
        TaskQueueFactory factory = new TaskQueueFactory(taskStore);

        Producer producer = factory.createProducer();
        Consumer consumer = factory.createConsumer();

        Task task1 = new Task("1", TaskType.PROCESS_ORDER, "{email: user@example.com}");
        task1.setPriority(TaskPriority.LOW);

        Task task2 = new Task("2", TaskType.SEND_EMAIL, "{orderId: 1002}");
        task2.setPriority(TaskPriority.HIGH);

        Task task3 = new Task("3", TaskType.GENERATE_REPORT, "{billingId: 111}");
        task3.setPriority(TaskPriority.MEDIUM);

        Task task4 = new Task("4", TaskType.SEND_EMAIL, "{orderId: 1004}");
        task4.setPriority(TaskPriority.HIGH);

        consumer.subscribe("topic1");

        CompletableFuture.allOf(
                producer.produce(task1, "topic1").thenAccept(messageId -> System.out.println("Produced: " + messageId)),
                producer.produce(task2, "topic1").thenAccept(messageId -> System.out.println("Produced: " + messageId)),
                producer.produce(task3, "topic1").thenAccept(messageId -> System.out.println("Produced: " + messageId)),
                producer.produce(task4, "topic1").thenAccept(messageId -> System.out.println("Produced: " + messageId))
        ).join();

        consumer.poll("topic1", 1000, 10).thenAccept(
                tasks -> tasks.forEach(
                        t -> System.out.println("Consumed: " + t.getId() + ", Priority: " + t.getPriority())
                )
        ).join();

        factory.shutdown();

        producer.close();
        consumer.close();
    }
}

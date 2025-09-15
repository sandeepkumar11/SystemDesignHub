
public class Main {
    public static void main(String[] args) {
        System.out.println("Distributed Task Queue...");
        System.out.println("*".repeat(50));

        TaskQueue taskQueue = new TaskQueue();
        taskQueue.demo();
    }
}
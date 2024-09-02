public class ConcurrencyExample {
    public static void main(String[] args) {
        System.out.println("Task 1 started");
        performTask("Task 1");

        System.out.println("Task 2 started");
        performTask("Task 2");
    }

    public static void performTask(String taskName) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(taskName + " - Step " + i);
            try {
                Thread.sleep(500); // Simulate time-consuming task
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

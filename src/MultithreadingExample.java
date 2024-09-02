public class MultithreadingExample {
    public static void main(String[] args) {
        Thread task1 = new Thread(() -> performTask("Task 1"));
        Thread task2 = new Thread(() -> performTask("Task 2"));

        task1.start(); // Task 1 and Task 2 will run concurrently
        task2.start();
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

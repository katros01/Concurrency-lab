import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PerformanceComparison {
    private static final int NUM_THREADS = 100;
    private static final int NUM_OPERATIONS = 10000;

    public static void main(String[] args) throws InterruptedException {
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        System.out.println("Testing with HashMap (not thread-safe):");
        measurePerformance(hashMap);

        System.out.println("\nTesting with ConcurrentHashMap (thread-safe):");
        measurePerformance(concurrentHashMap);
    }

    private static void measurePerformance(Map<String, Integer> map) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < NUM_OPERATIONS; j++) {
                    map.put("Key" + j, j);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        System.out.println("Final size of map: " + map.size());
    }
}


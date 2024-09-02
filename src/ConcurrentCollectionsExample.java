import java.util.concurrent.*;

public class ConcurrentCollectionsExample {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        CopyOnWriteArrayList<String> concurrentList = new CopyOnWriteArrayList<>();
        ConcurrentSkipListMap<String, Integer> skipListMap = new ConcurrentSkipListMap<>();
        ConcurrentSkipListSet<String> skipListSet = new ConcurrentSkipListSet<>();

        // Adding elements in ConcurrentHashMap
        Thread t1 = new Thread(() -> {
            concurrentMap.put("Key1", 1);
            concurrentMap.put("Key2", 2);
            concurrentMap.put("Key3", 3);
            System.out.println("ConcurrentMap: " + concurrentMap);
        });

        // Adding elements in CopyOnWriteArrayList
        Thread t2 = new Thread(() -> {
            concurrentList.add("Item1");
            concurrentList.add("Item2");
            concurrentList.add("Item3");
            System.out.println("ConcurrentList: " + concurrentList);
        });

        // Adding elements in ConcurrentSkipListMap
        Thread t3 = new Thread(() -> {
            skipListMap.put("KeyA", 10);
            skipListMap.put("KeyB", 20);
            skipListMap.put("KeyC", 30);
            System.out.println("ConcurrentSkipListMap: " + skipListMap);
        });

        // Adding elements in ConcurrentSkipListSet
        Thread t4 = new Thread(() -> {
            skipListSet.add("Alpha");
            skipListSet.add("Beta");
            skipListSet.add("Gamma");
            System.out.println("ConcurrentSkipListSet: " + skipListSet);
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();


        t1.join();
        t2.join();
        t3.join();
        t4.join();

        // Iterating ConcurrentHashMap while modifying
        Thread t5 = new Thread(() -> {
            concurrentMap.forEach((key, value) -> {
                System.out.println(key + ": " + value);
                concurrentMap.put("Key4", 4);
            });
        });

        // Iterating ConcurrentSkipListMap while modifying
        Thread t6 = new Thread(() -> {
            skipListMap.forEach((key, value) -> {
                System.out.println(key + ": " + value);
                skipListMap.put("KeyD", 40);
            });
        });

        t5.start();
        t6.start();

        t5.join();
        t6.join();

        System.out.println("ConcurrentMap after iteration: " + concurrentMap);
        System.out.println("ConcurrentSkipListMap after iteration: " + skipListMap);
    }
}

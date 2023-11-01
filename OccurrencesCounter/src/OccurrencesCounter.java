package src;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OccurrencesCounter {
    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length == 0)
            return;
        Map<String, Integer> map = new ConcurrentHashMap<String, Integer>(26);

        ExecutorService threadPool = Executors.newFixedThreadPool(args.length);
        for (int i = 0; i < args.length; i++) {
            threadPool.execute(new Task(args[i], map, i + 1));
        }
        threadPool.shutdown();
        threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

        FileWriter writer = new FileWriter("output.txt");
        map.forEach((key, value) -> {
            try {
                writer.write(key + "," + Integer.toString(value) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
        System.out.println("\nDone! Results are in output.txt");
    }
}
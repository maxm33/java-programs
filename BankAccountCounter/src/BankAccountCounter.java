package src;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class BankAccountCounter {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java -cp ./lib/gson-2.10.1.jar:. src.BankAccountCounter <number of threads>");
            return;
        }
        int numthreads;
        try {
            numthreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException a) {
            System.out.println("ERR- 'Not a number'.");
            return;
        }
        Map<String, Integer> map = new ConcurrentHashMap<String, Integer>(5);
        ExecutorService threadPool = Executors.newFixedThreadPool(numthreads); // number of threads
        File file = new File("accounts/accounts.json"); // pathname of accounts.json

        JsonReader reader = new JsonReader(new FileReader(file));
        reader.beginArray();
        while (reader.hasNext()) {
            BankAccount account = new Gson().fromJson(reader, BankAccount.class);
            threadPool.execute(new Task(account, map));
        }
        reader.endArray();
        reader.close();

        threadPool.shutdown();
        threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        map.forEach((key, value) -> System.out.println("# of " + key + ": " + Integer.toString(value)));
    }
}

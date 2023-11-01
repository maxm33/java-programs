package src;

import java.util.Map;

public class Task implements Runnable {
    public BankAccount account;
    public Map<String, Integer> map;

    public Task(BankAccount account, Map<String, Integer> map) {
        this.account = account;
        this.map = map;
    }

    @Override
    public void run() {
        this.account.records.forEach((transaction) -> {
            synchronized (map) {
                map.computeIfPresent(transaction.reason, (key, value) -> value + 1);
                map.putIfAbsent(transaction.reason, 1);
            }
        });
    }
}
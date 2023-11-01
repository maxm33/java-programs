package src;

import java.util.List;

public class BankAccount {
    public String owner;
    public List<Transaction> records;

    public BankAccount(String owner, List<Transaction> records) {
        this.owner = owner;
        this.records = records;
    }
}
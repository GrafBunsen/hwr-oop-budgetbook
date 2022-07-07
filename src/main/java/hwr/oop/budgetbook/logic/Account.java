package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Account {
    private final String category;
    private final Map<Integer, Entry> table;

    Account(String category, Map<Integer, Entry> table) {
        this.table = table;
        this.category = category;
    }

    public Account(String category) {
        this(category, new HashMap<>());
    }

    public Map<Integer, Entry> getTable() {
        return table;
    }

    public void addEntry(Transaction transaction) {
        int id = getHighestId() + 1;
        addEntry(id, transaction);
    }

    private int getHighestId() {
        int highestKey = 0;
        for (int key : table.keySet()) {
            if (key > highestKey) {
                highestKey = key;
            }
        }
        return highestKey;
    }

    private void addEntry(int id, Transaction transaction) {
        Entry entry = new Entry(id, transaction.getDate(), transaction.getAmount(), transaction.getCategory(), transaction.getDescription());
        table.put(id, entry);
    }

    public void removeEntry(int id) {
        table.remove(id);
    }

    public void removeLastEntry() {
        removeEntry(table.size());
    }

    public String getCategory() {
        return category;
    }

    public int sumOverAllEntries() {
        int sum = 0;
        for (int key : table.keySet()) {
            sum += table.get(key).getAmount();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Account account = (Account) o;
        // field comparison
        return Objects.equals(table, account.table);
    }
}

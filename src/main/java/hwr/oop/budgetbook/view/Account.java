package hwr.oop.budgetbook.view;

import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String path;
    private final List<Entry> table;

    Account(String path, List<Entry> table) {
        this.table = table;
        this.path = path;
    }

    public Account(String path) {
        this(path,new ArrayList<>());
    }

    public List<Entry> getTable() {
        return table;
    }

    public void addEntry(Transaction transaction) {
        String id = String.valueOf(table.size()+1);
        addEntry(Integer.parseInt(id), transaction);
    }

    public void addEntry(int id, Transaction transaction) {
        Entry entry = new Entry(id,transaction.getDate(),transaction.getAmount(),transaction.getCategory(),transaction.getDescription());
        table.add(id-1,entry);
        for (int i = id -1; i < table.size(); i++) {
            table.get(i).setId(i+1);
        }
    }

    public void removeEntry(int id) {
        table.remove(id-1);
        for (int i = id-1; i < table.size(); i++) {
            table.get(i).setId(i+1);
        }
    }

    public void removeLastEntry() {
        removeEntry(table.size() - 1);
    }

    public String getPath() {
        return path;
    }

    public int sumOverAllEntries() {
        int sum = 0;
        for (Entry entry : table) {
            sum += entry.getAmount();
        }
        return sum;
    }
}

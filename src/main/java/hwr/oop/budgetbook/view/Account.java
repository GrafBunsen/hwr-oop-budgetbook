package hwr.oop.budgetbook.view;

import hwr.oop.budgetbook.exceptions.InvalidLineException;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String path;
    private final List<List<String>> table;

    Account(String path, List<List<String>> table) {
        this.table = table;
        this.path = path;
    }

    public Account(String path) {
        this(path, createEmptyTable());
    }

    public List<List<String>> getTable() {
        return table;
    }

    public void addLine(List<String> line) {
        String id = String.valueOf(table.size());
        addLine(Integer.parseInt(id), line);
    }

    public void addLine(int id, List<String> line) {
        if (line.size() == (table.get(0).size() - 1)) {
            List<String> newLine = new ArrayList<>();
            newLine.add(String.valueOf(id));
            newLine.addAll(line);
            table.add(id, newLine);
        } else {
            throw new InvalidLineException("Tried adding an invalid Line");
        }
        for (int i = id + 1; i < table.size(); i++) {
            table.get(i).set(0, String.valueOf(i));
        }
    }

    public void removeLine(int id) {
        table.remove(id);
        for (int i = id; i < table.size(); i++) {
            table.get(i).set(0, String.valueOf(i));
        }
    }

    public void removeLastLine() {
        removeLine(table.size() - 1);
    }

    private static List<List<String>> createEmptyTable() {
        List<String> header = new ArrayList<>();
        header.add("ID");
        header.add("Datum");
        header.add("Betrag");
        header.add("Kategorie");
        header.add("Beschreibung");
        List<List<String>> table = new ArrayList<>();
        table.add(header);
        return table;
    }

    public String getPath() {
        return path;
    }

    public int sumOverAllEntries() {
        int sum = 0;
        for (int i = 1; i < table.size(); i++) {
            sum = sum + Integer.parseInt(table.get(i).get(2));
        }
        return sum;
    }
}

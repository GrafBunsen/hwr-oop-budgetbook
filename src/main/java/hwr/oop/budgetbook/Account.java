package hwr.oop.budgetbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private final String path;
    private final List<List<String>> table;

    Account(String path, boolean override) throws IOException {
        boolean doesExist = new File(path).exists();
        boolean append = doesExist & !override;

        this.path = path;

        if (append) {
            table = readCsvFile(path);
        } else {
            table = new ArrayList<>();
            List<String> header = createHeader();
            table.add(header);
        }
    }

    Account(String path) throws IOException {
        this(path, false);
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
            throw new RuntimeException("Invalid Line");
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

    private List<String> createHeader() {
        List<String> header = new ArrayList<>();
        header.add("ID");
        header.add("Datum");
        header.add("Betrag");
        header.add("Kategorie");
        header.add("Beschreibung");
        return header;
    }

    private List<List<String>> readCsvFile(String pathToCsv) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        String row;
        List<List<String>> table = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            String[] lineAsString = row.split(",");
            List<String> lineAsList = new ArrayList<>();
            Collections.addAll(lineAsList, lineAsString);
            table.add(lineAsList);
        }
        csvReader.close();
        return table;
    }

    public void saveTable() throws IOException {
        FileWriter csvWriter = new FileWriter(path);

        for (List<String> strings : table) {
            for (String string : strings) {
                csvWriter.append(string).append(",");
            }
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }
}

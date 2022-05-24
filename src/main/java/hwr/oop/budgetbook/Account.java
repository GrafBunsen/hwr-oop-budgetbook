package hwr.oop.budgetbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Account {
    private final String path;
    private final ArrayList<ArrayList<String>> table;

    Account(String path, boolean override) throws IOException {
        boolean doesExist = new File(path).exists();
        boolean append = doesExist & !override;

        this.path = path;

        if (append) {
            table = readCsvFile(path);
        } else {
            table = new ArrayList<>();
            ArrayList<String> header = createHeader();
            table.add(header);
        }
    }

    Account(String path) throws IOException {
        this(path, false);
    }

    public ArrayList<ArrayList<String>> getTable() {
        return table;
    }

    private ArrayList<String> createHeader() {
        ArrayList<String> header = new ArrayList<>();
        header.add("ID");
        header.add("Datum");
        header.add("Betrag");
        header.add("Kategorie");
        header.add("Beschreibung");
        return header;
    }

    public void addLine(ArrayList<String> line) {
        String id = String.valueOf(table.size());
        ArrayList<String> newLine = new ArrayList<>();
        newLine.add(id);
        newLine.addAll(line);
        table.add(newLine);
    }

    private ArrayList<ArrayList<String>> readCsvFile(String pathToCsv) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        String row;
        ArrayList<ArrayList<String>> table = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            String[] lineAsString = row.split(",");
            ArrayList<String> lineAsList = new ArrayList<>();
            Collections.addAll(lineAsList, lineAsString);
            table.add(lineAsList);
        }
        csvReader.close();
        return table;
    }

    public void saveTable() throws IOException {
        FileWriter csvWriter = new FileWriter(path);

        for (ArrayList<String> strings : table) {
            for (String string : strings) {
                csvWriter.append(string).append(",");
            }
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

}

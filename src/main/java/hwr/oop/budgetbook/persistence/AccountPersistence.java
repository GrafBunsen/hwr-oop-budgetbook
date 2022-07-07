package hwr.oop.budgetbook.persistence;

import hwr.oop.budgetbook.exceptions.ReadCsvFileFailedException;
import hwr.oop.budgetbook.exceptions.SaveTableFailedException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountPersistence {
    public static List<List<String>> readCsvFile(String pathToCsv) {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv))) {
            return readerGetsTable(csvReader);
        } catch (IOException e) {
            throw new ReadCsvFileFailedException("Could not read File");
        }
    }

    private static List<List<String>> readerGetsTable(BufferedReader csvReader) throws IOException {
        String row;
        List<List<String>> table = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            String[] lineAsString = row.split(",");
            List<String> lineAsList = new ArrayList<>();
            Collections.addAll(lineAsList, lineAsString);
            table.add(lineAsList);
        }
        return table;
    }

    public static void saveTable(List<List<String>> table, String path) {
        try (FileWriter csvWriter = new FileWriter(path)) {
            writerSavesTable(csvWriter, table);
        } catch (IOException e) {
            throw new SaveTableFailedException("Could not write File");
        }
    }

    private static void writerSavesTable(FileWriter csvWriter, List<List<String>> table) throws IOException {
        for (List<String> strings : table) {
            for (String string : strings) {
                csvWriter.append(string).append(",");
            }
            csvWriter.append("\n");
        }
        csvWriter.flush();
    }
}

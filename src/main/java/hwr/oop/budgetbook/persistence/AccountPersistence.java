package hwr.oop.budgetbook.persistence;

import hwr.oop.budgetbook.exceptions.ReadCsvFileFailedException;
import hwr.oop.budgetbook.exceptions.SaveTableFailedException;
import hwr.oop.budgetbook.logic.DoubleEntryBookkeepingAccount;
import hwr.oop.budgetbook.models.Entry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AccountPersistence {

    private final EntryListConverter entryListConverter;
    private final PersistenceConverter persistenceConverter;
    private final MapConverter mapConverter;

    AccountPersistence() {
        entryListConverter = new EntryListConverter();
        persistenceConverter = new PersistenceConverter();
        mapConverter = new MapConverter();
    }

    public DoubleEntryBookkeepingAccount readCsvFile(String pathToCsv) {
        List<List<String>> readTable;
        try (BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv))) {
            readTable = readerGetsTable(csvReader);
        } catch (IOException e) {
            throw new ReadCsvFileFailedException("Could not read File");
        }

        return convertDataForUsage(readTable);
    }

    private List<List<String>> readerGetsTable(BufferedReader csvReader) throws IOException {
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

    public void saveDoubleEntryBookKeepingAccount(DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount, String path) {

        List<List<String>> listTable = convertDataForSaving(doubleEntryBookkeepingAccount);

        try (FileWriter csvWriter = new FileWriter(path)) {
            writerSavesTable(csvWriter, listTable);
        } catch (IOException e) {
            throw new SaveTableFailedException("Could not write File");
        }
    }

    private void writerSavesTable(FileWriter csvWriter, List<List<String>> table) throws IOException {
        for (List<String> strings : table) {
            for (String string : strings) {
                csvWriter.append(string).append(",");
            }
            csvWriter.append("\n");
        }
        csvWriter.flush();
    }

    private DoubleEntryBookkeepingAccount convertDataForUsage(List<List<String>> convertableList) {
        List<List<String>> listWithoutHeader = persistenceConverter.convertForUsage(convertableList);
        Map<Integer, Entry> singleMap = entryListConverter.convertLines(listWithoutHeader);
        return mapConverter.toDoubleEntryBookkeepingAccount(singleMap);
    }

    private List<List<String>> convertDataForSaving(DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount) {
        Map<Integer, Entry> mapTable = mapConverter.toSingleMap(doubleEntryBookkeepingAccount);
        List<List<String>> rawList = entryListConverter.convertEntries(mapTable);
        return persistenceConverter.convertForPersistence(rawList);
    }
}

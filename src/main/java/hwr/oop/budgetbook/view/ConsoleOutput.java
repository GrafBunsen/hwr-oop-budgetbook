package hwr.oop.budgetbook.view;

import hwr.oop.budgetbook.logic.Account;
import hwr.oop.budgetbook.persistence.EntryListConverter;
import hwr.oop.budgetbook.persistence.PersistenceConverter;

import java.util.List;

public class ConsoleOutput {

    public void printTable(Account account) {
        List<List<String>> table = convertDataForSaving(account);
        int longestCell = getLongestCell(table);
        List<List<String>> output = makeAllCellsTheSameLength(table, longestCell);
        printTheCleanedOutput(output);
    }

    private int getLongestCell(List<List<String>> table) {
        int longestCell = 0;

        for (List<String> strings : table) {
            for (String string : strings) {
                if (longestCell < string.length()) {
                    longestCell = string.length();
                }
            }

        }
        return longestCell;
    }

    private List<List<String>> makeAllCellsTheSameLength(List<List<String>> output, int longestCell) {
        for (List<String> strings : output) {
            for (int j = 0; j < strings.size(); j++) {
                while (strings.get(j).length() < longestCell) {
                    strings.set(j, strings.get(j) + " ");
                }
            }
        }
        return output;
    }

    private void printTheCleanedOutput(List<List<String>> output) {
        for (List<String> strings : output) {
            System.out.print("| ");
            for (String string : strings) {
                System.out.print(string + " | ");
            }
            System.out.println();
        }
    }

    private List<List<String>> convertDataForSaving(Account account) {
        EntryListConverter entryListConverter = new EntryListConverter();
        PersistenceConverter persistenceConverter = new PersistenceConverter();

        List<List<String>> rawList = entryListConverter.convertEntries(account.getTable());
        return persistenceConverter.convertForPersistence(rawList);
    }
}

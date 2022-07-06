package hwr.oop.budgetbook.persistence;

import hwr.oop.budgetbook.exceptions.HeaderLineNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersistenceConverter {
    private final List<String> HEADER_LINE = new ArrayList<>();

    PersistenceConverter() {
        HEADER_LINE.add("ID");
        HEADER_LINE.add("Datum");
        HEADER_LINE.add("Betrag");
        HEADER_LINE.add("Kategorie");
        HEADER_LINE.add("Beschreibung");
    }

    public List<List<String>> convertForPersistence(List<List<String>> listToWrite) {
        List<List<String>> newList = new ArrayList<>();

        newList.add(HEADER_LINE);
        newList.addAll(listToWrite);

        return newList;
    }

    public List<List<String>> convertForUsage(List<List<String>> listToRead) throws HeaderLineNotFoundException {
        List<String> firstLine = listToRead.get(0);

        if (!Objects.equals(firstLine.get(0), "ID")) {
            throw new HeaderLineNotFoundException("The header line was not found.");
        }

        listToRead.remove(0);
        return listToRead;
    }
}

package hwr.oop.budgetbook.persistence;

import hwr.oop.budgetbook.models.Entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryListConverter {
    public Map<Integer, Entry> convertLines(List<List<String>> fileLines) {
        Map<Integer, Entry> convertedList = new HashMap<>();

        fileLines.forEach((List<String> line) -> convertedList.put(Integer.parseInt(line.get(0)), this.convertToEntry(line)));

        return convertedList;
    }

    private Entry convertToEntry(List<String> fileLine) {
        return new Entry(
                Integer.parseInt(fileLine.get(0)), Integer.parseInt(fileLine.get(1)),
                Integer.parseInt(fileLine.get(2)), fileLine.get(3), fileLine.get(4)
        );
    }

    public List<List<String>> convertEntries(Map<Integer, Entry> entryList) {
        List<List<String>> convertedList = new ArrayList<>();

        entryList.forEach((id, entry) -> convertedList.add(convertToList(entry)));

        return convertedList;
    }

    private List<String> convertToList(Entry entry) {
        List<String> list = new ArrayList<>();

        list.add(String.valueOf(entry.getId()));
        list.add(String.valueOf(entry.getDate()));
        list.add(String.valueOf(entry.getAmount()));
        list.add(entry.getCategory());
        list.add(entry.getDescription());

        return list;
    }
}

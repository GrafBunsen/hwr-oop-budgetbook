package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Entry;

import java.util.HashMap;
import java.util.List;

public class EntryListConverter {
    public HashMap<Integer, Entry> convertFromFileLines(List<List<String>> fileLines) {
        HashMap<Integer, Entry> convertedList = new HashMap<>();

        fileLines.forEach((List<String> line) -> {
            convertedList.put(Integer.parseInt(line.get(0)), this.convertToEntry(line));
        });

        return convertedList;
    }

    private Entry convertToEntry(List<String> fileLine) {
        return new Entry(
                Integer.parseInt(fileLine.get(0)), Integer.parseInt(fileLine.get(1)),
                Integer.parseInt(fileLine.get(2)), fileLine.get(3), fileLine.get(4)
        );
    }
}

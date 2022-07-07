package hwr.oop.budgetbook.logic;


import hwr.oop.budgetbook.models.Entry;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntryListConverterTest {
    @Test
    public void construct_canInstantiate_True() {
        EntryListConverter converter = new EntryListConverter();
        Assertions.assertThat(converter).isInstanceOf(EntryListConverter.class);
    }

    @Test
    public void convertLines_convertsListToHashMap_FirstItemIsNotNull() {
        List<List<String>> listOfStringLists = new ArrayList<>();

        List<String> line = new ArrayList<>();
        line.add("1");
        line.add("220124");
        line.add("300");
        line.add("Weihnachtsgeld");
        line.add("Peters Geschenk");

        listOfStringLists.add(line);

        EntryListConverter converter = new EntryListConverter();
        Map<Integer, Entry> convertedList = converter.convertLines(listOfStringLists);

        Assertions.assertThat(convertedList.get(1)).isNotNull();
    }

    @Test
    public void convertLines_convertsSameValuesToHashMap_FirstItemValuesAreEqual_True() {
        List<List<String>> listOfStringLists = new ArrayList<>();

        List<String> line = new ArrayList<>();
        line.add("1");
        line.add("220124");
        line.add("300");
        line.add("Weihnachtsgeld");
        line.add("Peters Geschenk");

        listOfStringLists.add(line);

        EntryListConverter converter = new EntryListConverter();
        Map<Integer, Entry> convertedList = converter.convertLines(listOfStringLists);

        Entry entryFromConvertedList = convertedList.get(1);

        Assertions.assertThat(entryFromConvertedList.getId()).isEqualTo(1);
        Assertions.assertThat(entryFromConvertedList.getDate()).isEqualTo(220124);
        Assertions.assertThat(entryFromConvertedList.getAmount()).isEqualTo(300);
        Assertions.assertThat(entryFromConvertedList.getCategory()).isEqualTo("Weihnachtsgeld");
        Assertions.assertThat(entryFromConvertedList.getDescription()).isEqualTo("Peters Geschenk");
    }

    @Test
    public void convertEntries_converterConvertsWithoutLoss_True() {
        List<List<String>> listOfStringLists = new ArrayList<>();

        List<String> line = new ArrayList<>();
        line.add("1");
        line.add("220124");
        line.add("300");
        line.add("Weihnachtsgeld");
        line.add("Peters Geschenk");

        listOfStringLists.add(line);

        EntryListConverter converter = new EntryListConverter();
        Map<Integer, Entry> convertedList = converter.convertLines(listOfStringLists);

        List<List<String>> convertedListOfStringLists = converter.convertEntries(convertedList);

        Assertions.assertThat(listOfStringLists).isEqualTo(convertedListOfStringLists);
    }
}

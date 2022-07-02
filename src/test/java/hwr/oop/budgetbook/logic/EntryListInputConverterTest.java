package hwr.oop.budgetbook.logic;


import hwr.oop.budgetbook.models.Entry;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntryListInputConverterTest {
    @Test
    public void test_instanciation() {
        EntryListConverter converter = new EntryListConverter();
        Assertions.assertThat(converter).isInstanceOf(EntryListConverter.class);
    }

    @Test
    public void test_converterConvertsOneEntry_isNotNull() {
        List<List<String>> listOfStringLists = new ArrayList<>();

        List<String> line = new ArrayList<>();
        line.add("1");
        line.add("220124");
        line.add("300");
        line.add("Weihnachtsgeld");
        line.add("Peters Geschenk");

        listOfStringLists.add(line);

        EntryListConverter converter = new EntryListConverter();
        HashMap<Integer, Entry> convertedList = converter.convertFromFileLines(listOfStringLists);

        Assertions.assertThat(convertedList.get(1)).isNotNull();
    }

    @Test
    public void test_converterConvertsOneEntry_thatTheOneEntryHasTheSameAttributes() {
        List<List<String>> listOfStringLists = new ArrayList<>();

        List<String> line = new ArrayList<>();
        line.add("1");
        line.add("220124");
        line.add("300");
        line.add("Weihnachtsgeld");
        line.add("Peters Geschenk");

        listOfStringLists.add(line);

        EntryListConverter converter = new EntryListConverter();
        HashMap<Integer, Entry> convertedList = converter.convertFromFileLines(listOfStringLists);

        Entry entryFromConvertedList = convertedList.get(1);

        Assertions.assertThat(entryFromConvertedList.getId()).isEqualTo(1);
        Assertions.assertThat(entryFromConvertedList.getDate()).isEqualTo(220124);
        Assertions.assertThat(entryFromConvertedList.getAmount()).isEqualTo(300);
        Assertions.assertThat(entryFromConvertedList.getCategory()).isEqualTo("Weihnachtsgeld");
        Assertions.assertThat(entryFromConvertedList.getDescription()).isEqualTo("Peters Geschenk");
    }
}

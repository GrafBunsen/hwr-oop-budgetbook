package hwr.oop.budgetbook.persistence;


import hwr.oop.budgetbook.exceptions.HeaderLineNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PersistenceConverterTest {
    static private final List<String> HEADER_LINE = new ArrayList<>();

    @BeforeAll
    static void setupTest() {
        HEADER_LINE.add("ID");
        HEADER_LINE.add("Datum");
        HEADER_LINE.add("Betrag");
        HEADER_LINE.add("Kategorie");
        HEADER_LINE.add("Beschreibung");
    }

    @Test
    public void constructor_IsNotConstructed_Success() {
        PersistenceConverter converter = new PersistenceConverter();
        Assertions.assertThat(converter).isInstanceOf(PersistenceConverter.class);
    }

    @Test
    public void convertForPersistence_listHasNotTheHeaderIncluded_addedTheHeaderLineAtIndexOne() {
        PersistenceConverter converter = new PersistenceConverter();
        List<List<String>> listToWrite = new ArrayList<>();
        List<String> itemInThisList = new ArrayList<>();

        itemInThisList.add("1");
        itemInThisList.add("20220701");
        itemInThisList.add("300");
        itemInThisList.add("Hobby");
        itemInThisList.add("Bouldern");

        listToWrite.add(itemInThisList);

        List<List<String>> convertedList = converter.convertForPersistence(listToWrite);

        Assertions.assertThat(convertedList.get(0)).isEqualTo(HEADER_LINE);
    }

    @Test
    public void convertForUsage_listHasTheHeaderIncluded_removedTheHeaderLineAtIndexOne() {
        PersistenceConverter converter = new PersistenceConverter();
        List<List<String>> listToRead = new ArrayList<>();
        List<String> itemInThisList = new ArrayList<>();

        listToRead.add(HEADER_LINE);

        itemInThisList.add("1");
        itemInThisList.add("20220701");
        itemInThisList.add("300");
        itemInThisList.add("Hobby");
        itemInThisList.add("Bouldern");
        listToRead.add(itemInThisList);

        List<List<String>> convertedList = converter.convertForUsage(listToRead);

        Assertions.assertThat(convertedList.get(0)).isNotEqualTo(HEADER_LINE);
    }

    @Test
    public void convertForUsage_listHasNotTheHeaderIncluded_throwsHeaderLineNotFoundException() {
        PersistenceConverter converter = new PersistenceConverter();
        List<List<String>> listToRead = new ArrayList<>();
        List<String> itemInThisList = new ArrayList<>();

        itemInThisList.add("1");
        itemInThisList.add("20220701");
        itemInThisList.add("300");
        itemInThisList.add("Hobby");
        itemInThisList.add("Bouldern");
        listToRead.add(itemInThisList);

        try {
            List<List<String>> convertedList = converter.convertForUsage(listToRead);
        } catch (HeaderLineNotFoundException error) {
            Assertions.assertThat(error).isInstanceOf(HeaderLineNotFoundException.class);
        }
    }
}

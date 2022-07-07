package hwr.oop.budgetbook.persistence;

import hwr.oop.budgetbook.logic.DoubleEntryBookkeepingAccount;
import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MapConverterTest {

    @Test
    public void toSingleMap_DoubleEntryIsGiven_IncomeGetsReturned() {
        MapConverter mapConverter = new MapConverter();
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Map<Integer, Entry> singleMap = mapConverter.toSingleMap(doubleEntryBookkeepingAccount);
        Map<Integer, Entry> expectedMap = new HashMap<>();
        Entry expectedEntry = getExpectedEntry();
        expectedMap.put(1, expectedEntry);

        assertThat(singleMap).isEqualTo(expectedMap);
    }

    @Test
    public void toDoubleEntryBookkeepingAccount_SingleMapIsGiven_DoubleEntryIsReturned() {
        MapConverter mapConverter = new MapConverter();

        Transaction testTransaction = getTestTransaction();
        Transaction differentTransaction = new Transaction(220103, -100, "Fixkosten", "Strom");
        Entry testEntry = new Entry(1, testTransaction);
        Entry differentEntry = new Entry(2, differentTransaction);

        Map<Integer, Entry> givenMap = new HashMap<>();
        givenMap.put(1, testEntry);
        givenMap.put(2, differentEntry);

        DoubleEntryBookkeepingAccount givenAccount = mapConverter.toDoubleEntryBookkeepingAccount(givenMap);
        DoubleEntryBookkeepingAccount expectedAccount = new DoubleEntryBookkeepingAccount();
        expectedAccount.addTransaction(testTransaction);
        expectedAccount.addTransaction(differentTransaction);

        assertThat(givenAccount).isEqualTo(expectedAccount);
    }

    private Transaction getTestTransaction() {
        return new Transaction(220102, -50, "Einkauf", "Wocheneinkauf REWE");
    }

    private Entry getExpectedEntry() {
        return new Entry(1, getTestTransaction());
    }
}

package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountTest {

    private Transaction getTestTransaction() {
        return new Transaction(220102, 50, "Einkauf", "Wocheneinkauf REWE");
    }

    private Entry getExpectedEntry(int id) {
        return new Entry(id, getTestTransaction());
    }

    @Test
    void getPath_DoesReturnSpecifiedPath() {
        String path = "./src/test/resources/testPath.csv";
        Account account = new Account(path);
        assertThat(account.getCategory()).isEqualTo(path);
    }

    @Test
    public void sumOverAllEntries_givesSumOfAllEntries() {
        String path = "./src/test/resources/testSumOverAllEntries.csv";
        Transaction givenTransaction = getTestTransaction();
        Account account = new Account(path);

        account.addEntry(givenTransaction);
        account.addEntry(givenTransaction);
        account.addEntry(givenTransaction);
        assertThat(account.sumOverAllEntries()).isEqualTo(150);
    }

    @Nested
    class AddEntryTest {

        @Test
        void addEntry_givenLineIsExpectedToBePartOfTheTable() {
            Transaction givenTransaction = getTestTransaction();
            Entry expectedEntry = getExpectedEntry(1);

            String path = "./src/test/resources/testAddLine.csv";
            Account account = new Account(path);
            account.addEntry(givenTransaction);

            assertThat(account.getTable()).containsValues(expectedEntry);
        }
    }

    @Nested
    class RemoveEntryTest {
        @Test
        void removeEntry_aLineSpecifiedByIdIsRemoved() {
            String path = "./src/test/resources/testRemoveLine.csv";
            Transaction givenTransaction = getTestTransaction();
            Entry expectedEntry = getExpectedEntry(1);
            Account account = new Account(path);

            account.addEntry(givenTransaction);
            account.removeEntry(1);
            assertThat(account.getTable()).doesNotContainValue(expectedEntry);
        }

        @Test
        void removeLastEntry_LastLineOfTheTableIsRemoved() {
            String path = "./src/test/resources/testRemoveLine.csv";
            Transaction givenTransaction = getTestTransaction();
            Entry expectedEntry = getExpectedEntry(3);
            Account account = new Account(path);

            account.addEntry(givenTransaction);
            account.addEntry(givenTransaction);
            account.addEntry(givenTransaction);
            account.removeLastEntry();
            assertThat(account.getTable()).doesNotContainValue(expectedEntry);
        }
    }
}


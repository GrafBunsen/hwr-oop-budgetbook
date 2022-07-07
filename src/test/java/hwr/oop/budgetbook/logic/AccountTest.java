package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountTest {

    @Test
    void getCategory_testCategoryIsGivenAndReturned_isTrue() {
        String category = "Test";
        Account account = new Account(category);
        assertThat(account.getCategory()).isEqualTo(category);
    }

    @Test
    public void sumOverAllEntries_givenThreeEntries_returnsSum() {
        Transaction givenTransaction = getTestTransaction();
        Account account = new Account("Test");

        account.addEntry(givenTransaction);
        account.addEntry(givenTransaction);
        account.addEntry(givenTransaction);
        assertThat(account.sumOverAllEntries()).isEqualTo(150);
    }

    @Nested
    class AddEntryTest {

        @Test
        void addEntry_EntryIsGiven_isAddedToTable() {
            Transaction givenTransaction = getTestTransaction();
            Entry expectedEntry = getExpectedEntry(1);

            Account account = new Account("Test");
            account.addEntry(givenTransaction);

            assertThat(account.getTable()).containsValues(expectedEntry);
        }
    }

    @Nested
    class RemoveEntryTest {
        @Test
        void removeEntry_anEntryIsSpecifiedById_IsRemoved() {
            Transaction givenTransaction = getTestTransaction();
            Entry expectedEntry = getExpectedEntry(1);
            Account account = new Account("Test");

            account.addEntry(givenTransaction);
            account.removeEntry(1);
            assertThat(account.getTable()).doesNotContainValue(expectedEntry);
        }

        @Test
        void removeLastEntry_TableIsGiven_LastEntryIsRemoved() {
            Transaction givenTransaction = getTestTransaction();
            Entry expectedEntry = getExpectedEntry(3);
            Account account = new Account("Test");

            account.addEntry(givenTransaction);
            account.addEntry(givenTransaction);
            account.addEntry(givenTransaction);
            account.removeLastEntry();
            assertThat(account.getTable()).doesNotContainValue(expectedEntry);
        }
    }

    private Transaction getTestTransaction() {
        return new Transaction(220102, 50, "Einkauf", "Wocheneinkauf REWE");
    }

    private Entry getExpectedEntry(int id) {
        return new Entry(id, getTestTransaction());
    }
}


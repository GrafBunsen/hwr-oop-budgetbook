package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;
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

    @Test
    void addEntry_EntryIsGiven_isAddedToTable() {
        Transaction givenTransaction = getTestTransaction();
        Entry expectedEntry = getExpectedEntry();
        

        Account account = new Account("Test");
        account.addEntry(givenTransaction);

        assertThat(account.getTable()).containsValues(expectedEntry);
    }
    
    @Test
    void removeEntry_anEntryIsGiven_IsRemoved() {
        Transaction givenTransaction = getTestTransaction();
        Entry expectedEntry = getExpectedEntry();
        Account account = new Account("Test");

        account.addEntry(givenTransaction);
        account.removeEntry(givenTransaction);
        assertThat(account.getTable()).doesNotContainValue(expectedEntry);
    }
    

    @Test
    void equals_sameObject_isTrue() {
        Account account = new Account("Test");
        assertThat(account).isEqualTo(account);
    }

    @Test
    void equals_nullObject_isFalse() {
        Account account = new Account("Test");
        assertThat(account).isNotEqualTo(null);
    }

    @Test
    void equals_differentKindOfObject_isFalse() {
        Account account = new Account("Test");
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        assertThat(account).isNotEqualTo(doubleEntryBookkeepingAccount);
    }

    @Test
    void equals_equalObject_isTrue() {
        Account account = new Account("Test");
        Account equalAccount = new Account("Test");
        assertThat(account).isEqualTo(equalAccount);
    }


    private Transaction getTestTransaction() {
        return new Transaction(220102, 50, "Einkauf", "Wocheneinkauf REWE");
    }

    private Entry getExpectedEntry() {
        return new Entry(1, getTestTransaction());
    }
}


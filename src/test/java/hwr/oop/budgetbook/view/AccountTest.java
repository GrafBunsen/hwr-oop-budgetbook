package hwr.oop.budgetbook.view;

import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountTest {

    private Transaction getTestTransaction() {
        return new Transaction(220102,50,"Einkauf","Wocheneinkauf REWE");
    }

    private Entry getExpectedEntry(int id) {
        return new Entry(id,getTestTransaction());
    }

    @Test
    void getPath_DoesReturnSpecifiedPath() {
        String path = "./src/test/resources/testPath.csv";
        Account account = new Account(path);
        assertThat(account.getPath()).isEqualTo(path);
    }

    @Test
    public void sumOverAllEntries_givesSumOfAllEntries(){
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
            assertThat(account.getTable().get(0)).isEqualTo(expectedEntry);
        }

        @Test
        void addEntry_IdIsIncremented() {
            Transaction givenTransaction = getTestTransaction();
            String path = "./src/test/resources/testAddLine.csv";

            Account account = new Account(path);
            account.addEntry(givenTransaction);
            List<Entry> table = account.getTable();
            int id = table.get(table.size() - 1).getId();
            int expectedId = 1;
            assertThat(id).isEqualTo(expectedId);
        }

        @Test
        void addEntry_LineIsInsertedAtSpecifiedID() {
            String path = "./src/test/resources/testInsertLine.csv";
            Transaction givenTransaction = getTestTransaction();
            Transaction differentTransaction = getTestTransaction();
            differentTransaction.setAmount(99);
            Entry expectedEntry = getExpectedEntry(1);
            Account account = new Account(path);
            account.addEntry(differentTransaction);
            account.addEntry(differentTransaction);
            account.addEntry(differentTransaction);
            account.addEntry(1, givenTransaction);
            assertThat(account.getTable().get(0)).isEqualTo(expectedEntry);
        }

        @Test
        void addEntry_IdsAreStillCountingUpAfterALineIsAddedAtASpecifiedID() {
            String path = "./src/test/resources/testInsertLine.csv";
            Transaction givenTransaction = getTestTransaction();
            Account account = new Account(path);

            account.addEntry(givenTransaction);
            account.addEntry(givenTransaction);
            account.addEntry(1, givenTransaction);
            int firstId = account.getTable().get(0).getId();
            int secondId = account.getTable().get(1).getId();
            int thirdId = account.getTable().get(2).getId();
            assertThat(firstId).isEqualTo(1);
            assertThat(secondId).isEqualTo(2);
            assertThat(thirdId).isEqualTo(3);
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
            assertThat(account.getTable()).doesNotContain(expectedEntry);
        }

        @Test
        void removeEntry_IdsAreStillCountingUpAfterALineIsRemoved() {
            String path = "./src/test/resources/testRemoveLine.csv";
            Transaction givenTransaction = getTestTransaction();
            Account account = new Account(path);

            account.addEntry(givenTransaction);
            account.addEntry(givenTransaction);
            account.addEntry(givenTransaction);
            account.removeEntry(1);
            int firstId = account.getTable().get(0).getId();
            int secondId = account.getTable().get(1).getId();
            assertThat(firstId).isEqualTo(1);
            assertThat(secondId).isEqualTo(2);
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
            assertThat(account.getTable()).doesNotContain(expectedEntry);
        }
    }
}


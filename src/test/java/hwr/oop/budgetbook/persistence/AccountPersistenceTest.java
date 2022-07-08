package hwr.oop.budgetbook.persistence;

import hwr.oop.budgetbook.exceptions.ReadCsvFileFailedException;
import hwr.oop.budgetbook.exceptions.SaveTableFailedException;
import hwr.oop.budgetbook.logic.DoubleEntryBookkeepingAccount;
import hwr.oop.budgetbook.models.Transaction;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountPersistenceTest {

    private Transaction getTestTransaction() {
        return new Transaction(220102, -50, "Einkauf", "Wocheneinkauf REWE");
    }

    @Nested
    class ReadTableTest {
        @Test
        void readCsvFile_ReadsExistingTable_isAsExpected() {
            String path = "./src/test/resources/testPersistence.csv";
            AccountPersistence accountPersistence = new AccountPersistence();

            DoubleEntryBookkeepingAccount expectedAccount = new DoubleEntryBookkeepingAccount();
            expectedAccount.addTransaction(getTestTransaction());

            DoubleEntryBookkeepingAccount readAccount = accountPersistence.readCsvFile(path);

            assertThat(expectedAccount).isEqualTo(readAccount);
        }

        @Test
        void readCsvFile_TableIsNotRead_throwsException() {
            String path = "./src/test/resources/testSaveTableNotPossible"; //this file mustn't exist
            Throwable thrown = catchReadThrowable(path);
            assertThat(thrown).isInstanceOf(ReadCsvFileFailedException.class).hasMessageContaining("Could not read File");
        }

        private Throwable catchReadThrowable(String path) {
            AccountPersistence accountPersistence = new AccountPersistence();

            Throwable thrown = null;
            try {
                accountPersistence.readCsvFile(path);
            } catch (ReadCsvFileFailedException e) {
                e.printStackTrace();
                thrown = e;
            }
            return thrown;
        }
    }

    @Nested
    class SaveTableTest {

        @Test
        void saveTable_ifALineIsAddedAndTheFileIsSaved_lineIsPartOfTableAfterReading() {
            AccountPersistence accountPersistence = new AccountPersistence();

            String path = "./src/test/resources/testSaveTable.csv";

            DoubleEntryBookkeepingAccount givenAccount = new DoubleEntryBookkeepingAccount();
            givenAccount.addTransaction(getTestTransaction());

            accountPersistence.saveDoubleEntryBookKeepingAccount(givenAccount, path);

            DoubleEntryBookkeepingAccount readAccount = accountPersistence.readCsvFile(path);

            assertThat(givenAccount).isEqualTo(readAccount);
            //assertThat(givenAccount.getIncome()).isEqualTo(readAccount.getIncome());
        }

        @Test
        void saveTable_SavingIsNotPossible_ThrowsException() {
            String path = "./src/test/resources/testSaveTableNotPossible.csv"; //this file has to be read-only
            DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
            doubleEntryBookkeepingAccount.addTransaction(getTestTransaction());
            Throwable thrown = catchSaveThrowable(doubleEntryBookkeepingAccount, path);
            assertThat(thrown).isInstanceOf(SaveTableFailedException.class).hasMessageContaining("Could not write File");
        }

        private Throwable catchSaveThrowable(DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount, String path) {
            Throwable thrown = null;
            AccountPersistence accountPersistence = new AccountPersistence();
            try {
                accountPersistence.saveDoubleEntryBookKeepingAccount(doubleEntryBookkeepingAccount, path);
            } catch (SaveTableFailedException e) {
                e.printStackTrace();
                thrown = e;
            }
            return thrown;
        }
    }
}

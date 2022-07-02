package hwr.oop.budgetbook.persistence;

import hwr.oop.budgetbook.exceptions.ReadCsvFileFailedException;
import hwr.oop.budgetbook.exceptions.SaveTableFailedException;
import hwr.oop.budgetbook.view.Account;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountPersistenceTest {
    private List<String> getHeader() {
        List<String> header = new ArrayList<>();
        header.add("ID");
        header.add("Datum");
        header.add("Betrag");
        header.add("Kategorie");
        header.add("Beschreibung");
        return header;
    }

    private List<String> getTestLine() {
        List<String> givenLine = new ArrayList<>();
        givenLine.add("220102");
        givenLine.add("50");
        givenLine.add("Einkauf");
        givenLine.add("Wocheneinkauf REWE");
        return givenLine;
    }

    private List<String> getExpectedLine() {
        List<String> expectedLine = new ArrayList<>();
        expectedLine.add("1");
        expectedLine.addAll(getTestLine());
        return expectedLine;
    }

    @Nested
    class ReadTableTest {
        @Test
        void readCsvFile_ReadsExistingTable() {
            String path = "./src/test/resources/testPersistence.csv";

            List<List<String>> expectedTable = new ArrayList<>();
            List<String> line1 = getHeader();
            List<String> line2 = getExpectedLine();
            expectedTable.add(line1);
            expectedTable.add(line2);

            List<List<String>> readTable = AccountPersistence.readCsvFile(path);

            assertThat(readTable).isEqualTo(expectedTable);
        }

        @Test
        void readCsvFile_TableIsNotRead() {
            String path = "./src/test/resources/testSaveTableNotPossible"; //this file mustn't exist
            Throwable thrown = catchReadThrowable(path);
            assertThat(thrown).isInstanceOf(ReadCsvFileFailedException.class).hasMessageContaining("Could not read File");
        }

        private Throwable catchReadThrowable(String path) {
            Throwable thrown = null;
            try {
                AccountPersistence.readCsvFile(path);
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
        void saveTable_ifALineIsAddedAndTheFileIsSavedItIsPartOfTheTableAfterReadingAgain() {
            List<String> givenLine = getTestLine();
            String path = "./src/test/resources/testSaveTable.csv";

            Account account = new Account(path);

            account.addLine(givenLine);
            AccountPersistence.saveTable(account.getTable(), account.getPath());

            List<List<String>> savedTable = account.getTable();
            List<List<String>> readTable = AccountPersistence.readCsvFile(path);

            assertThat(savedTable).isEqualTo(readTable);
        }

        @Test
        void saveTable_anExceptionIsThrownWhenSavingIsNotPossible() {
            String path = "./src/test/resources/testSaveTableNotPossible.csv"; //this file has to be read-only
            Account account = new Account(path);
            Throwable thrown = catchSaveThrowable(account);
            assertThat(thrown).isInstanceOf(SaveTableFailedException.class).hasMessageContaining("Could not write File");
        }

        private Throwable catchSaveThrowable(Account account) {
            Throwable thrown = null;
            try {
                AccountPersistence.saveTable(account.getTable(), account.getPath());
            } catch (SaveTableFailedException e) {
                e.printStackTrace();
                thrown = e;
            }
            return thrown;
        }
    }
}

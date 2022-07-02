package hwr.oop.budgetbook;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountTest {

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

    @Test
    void getPath_DoesReturnSpecifiedPath() {
        String path = "./src/test/resources/testPath.csv";
        Account account = new Account(path);
        assertThat(account.getPath()).isEqualTo(path);
    }

    @Nested
    class ConstructorTest {
        @Test
        void Account_TableIsCreatedEmpty() {
            String path = "./src/test/resources/testPersistence.csv";
            List<List<String>> expectedTable = new ArrayList<>();
            List<String> line1 = getHeader();
            expectedTable.add(line1);
            Account overrideAccount = new Account(path);
            assertThat(overrideAccount.getTable()).isEqualTo(expectedTable);
        }
    }

    @Nested
    class AddLineTest {

        @Test
        void addLine_givenLineIsExpectedToBePartOfTheTable() {
            List<String> givenLine = getTestLine();
            List<String> expectedLine = new ArrayList<>();
            expectedLine.add("2");
            expectedLine.add("220102");
            expectedLine.add("50");
            expectedLine.add("Einkauf");
            expectedLine.add("Wocheneinkauf REWE");
            String path = "./src/test/resources/testAddLine.csv";
            List<List<String>> table = AccountPersistence.readCsvFile(path);

            Account account = new Account(path, table);

            account.addLine(givenLine);
            assertThat(account.getTable()).contains(expectedLine);
        }

        @Test
        void addLine_IdIsIncremented() {
            List<String> givenLine = getTestLine();
            String path = "./src/test/resources/testAddLine.csv";

            Account account = new Account(path);
            account.addLine(givenLine);
            List<List<String>> table = account.getTable();
            String id = table.get(table.size() - 1).get(0);
            String expectedId = String.valueOf(table.size() - 1);
            assertThat(id).isEqualTo(expectedId);
        }

        @Test
        void addLine_checkIfLineIsValid_invalidLineIsRejected() {
            String path = "./src/test/resources/testAddLine.csv";
            Account account = new Account(path);

            List<String> invalidLine = getTestLine();
            invalidLine.remove(1);
            Throwable thrown = catchThrowable(account, invalidLine);

            assertThat(thrown).isInstanceOf(InvalidLineException.class).hasMessageContaining("Tried adding an invalid Line");
        }

        private Throwable catchThrowable(Account account, List<String> invalidLine) {
            Throwable thrown = null;
            try {
                account.addLine(invalidLine);
            } catch (InvalidLineException e) {
                e.printStackTrace();
                thrown = e;
            }
            return thrown;
        }

        @Test
        void addLine_checkIfLineIsValid_validLineIsAccepted() {
            String path = "./src/test/resources/testAddLine.csv";
            Account account = new Account(path);

            List<String> validLine = getTestLine();
            account.addLine(validLine);
            String id = String.valueOf(account.getTable().size() - 1);
            validLine.add(0, id);
            assertThat(account.getTable()).contains(validLine);
        }

        @Test
        void addLine_LineIsInsertedAtSpecifiedID() {
            String path = "./src/test/resources/testInsertLine.csv";
            List<String> givenLine = getTestLine();
            List<String> differentLine = getTestLine();
            differentLine.set(2, "99");
            List<String> expectedLine = getExpectedLine();
            Account account = new Account(path);
            account.addLine(differentLine);
            account.addLine(differentLine);
            account.addLine(differentLine);
            account.addLine(1, givenLine);
            assertThat(account.getTable().get(1)).isEqualTo(expectedLine);
        }

        @Test
        void addLine_IdsAreStillCountingUpAfterALineIsAddedAtASpecifiedID() {
            String path = "./src/test/resources/testInsertLine.csv";
            List<String> givenLine = getTestLine();
            Account account = new Account(path);

            account.addLine(givenLine);
            account.addLine(givenLine);
            account.addLine(1, givenLine);
            String firstId = account.getTable().get(1).get(0);
            String secondId = account.getTable().get(2).get(0);
            String thirdId = account.getTable().get(3).get(0);
            assertThat(firstId).isEqualTo("1");
            assertThat(secondId).isEqualTo("2");
            assertThat(thirdId).isEqualTo("3");
        }
    }

    @Nested
    class RemoveLineTest {
        @Test
        void removeLine_aLineSpecifiedByIdIsRemoved() {
            String path = "./src/test/resources/testRemoveLine.csv";
            List<String> givenLine = getTestLine();
            List<String> expectedLine = getExpectedLine();
            Account account = new Account(path);

            account.addLine(givenLine);
            account.removeLine(1);
            assertThat(account.getTable()).doesNotContain(expectedLine);
        }

        @Test
        void removeLine_IdsAreStillCountingUpAfterALineIsRemoved() {
            String path = "./src/test/resources/testRemoveLine.csv";
            List<String> givenLine = getTestLine();
            Account account = new Account(path);

            account.addLine(givenLine);
            account.addLine(givenLine);
            account.addLine(givenLine);
            account.removeLine(1);
            String firstId = account.getTable().get(1).get(0);
            String secondId = account.getTable().get(2).get(0);
            assertThat(firstId).isEqualTo("1");
            assertThat(secondId).isEqualTo("2");
        }

        @Test
        void removeLastLine_LastLineOfTheTableIsRemoved() {
            String path = "./src/test/resources/testRemoveLine.csv";
            List<String> givenLine = getTestLine();
            List<String> expectedLine = getExpectedLine();
            expectedLine.set(0, "3");
            Account account = new Account(path);

            account.addLine(givenLine);
            account.addLine(givenLine);
            account.addLine(givenLine);
            account.removeLastLine();
            assertThat(account.getTable()).doesNotContain(expectedLine);
        }
    }
}


package hwr.oop.budgetbook;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountTest {

    @Nested
    class ConstructorTest {
        @Test
        void Account_constructorReadsExistingTable() {
            String path = ".\\src\\test\\resources\\testConstructor.csv";

            try {
                Account account = new Account(path);
                ArrayList<ArrayList<String>> expectedTable = new ArrayList<>();
                ArrayList<String> line1 = getHeader();
                ArrayList<String> line2 = getExpectedLine();

                expectedTable.add(line1);
                expectedTable.add(line2);

                assertThat(account.getTable()).isEqualTo(expectedTable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Test
        void Account_TableIsAppendedOrCreatedEmpty() {
            String path = ".\\src\\test\\resources\\testConstructor.csv";
            ArrayList<ArrayList<String>> expectedTableAppend = new ArrayList<>();
            ArrayList<ArrayList<String>> expectedTableOverride = new ArrayList<>();
            ArrayList<String> line1 = getHeader();
            ArrayList<String> line2 = getExpectedLine();
            expectedTableAppend.add(line1);
            expectedTableAppend.add(line2);
            expectedTableOverride.add(line1);


            try {
                Account appendedAccount = new Account(path, false);
                assertThat(appendedAccount.getTable()).isEqualTo(expectedTableAppend);
                Account overrideAccount = new Account(path, true);
                assertThat(overrideAccount.getTable()).isEqualTo(expectedTableOverride);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Nested
    class AddLineTest {

        @Test
        void addLine_givenLineIsExpectedToBePartOfTheTable() {
            ArrayList<String> givenLine = getTestLine();
            ArrayList<String> expectedLine = new ArrayList<>();
            expectedLine.add("2");
            expectedLine.add("220102");
            expectedLine.add("50");
            expectedLine.add("Einkauf");
            expectedLine.add("Wocheneinkauf REWE");
            String path = ".\\src\\test\\resources\\testAddLine.csv";

            try {
                Account account = new Account(path);
                account.addLine(givenLine);
                assertThat(account.getTable()).contains(expectedLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Test
        void addLine_IdIsIncremented() {
            ArrayList<String> givenLine = getTestLine();
            String path = ".\\src\\test\\resources\\testAddLine.csv";

            try {
                Account account = new Account(path);
                account.addLine(givenLine);
                ArrayList<ArrayList<String>> table = account.getTable();
                String id = table.get(table.size() - 1).get(0);
                String expectedId = String.valueOf(table.size() - 1);
                assertThat(id).isEqualTo(expectedId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Test
        void addLine_checkIfLineIsValid_invalidLineIsRejected() {
            String path = ".\\src\\test\\resources\\testAddLine.csv";
            try {
                Account account = new Account(path);
                ArrayList<String> invalidLine = getTestLine();
                invalidLine.remove(1);
                account.addLine(invalidLine);
                String id = String.valueOf(account.getTable().size() - 1);
                invalidLine.add(0, id);
                assertThat(account.getTable()).doesNotContain(invalidLine);
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
            }
        }

        @Test
        void addLine_checkIfLineIsValid_validLineIsAccepted() {
            String path = ".\\src\\test\\resources\\testAddLine.csv";
            try {
                Account account = new Account(path);
                ArrayList<String> validLine = getTestLine();
                account.addLine(validLine);
                String id = String.valueOf(account.getTable().size() - 1);
                validLine.add(0, id);
                assertThat(account.getTable()).contains(validLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Test
        void addLine_LineIsInsertedAtSpecifiedID() {
            String path = ".\\src\\test\\resources\\testInsertLine.csv";
            ArrayList<String> givenLine = getTestLine();
            ArrayList<String> differentLine = getTestLine();
            differentLine.set(2, "99");
            ArrayList<String> expectedLine = getExpectedLine();
            try {
                Account account = new Account(path, true);
                account.addLine(differentLine);
                account.addLine(differentLine);
                account.addLine(differentLine);
                account.addLine(1, givenLine);
                assertThat(account.getTable().get(1)).isEqualTo(expectedLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Test
        void addLine_IdsAreStillCountingUpAfterALineIsAddedAtASpecifiedID() {
            String path = ".\\src\\test\\resources\\testInsertLine.csv";
            ArrayList<String> givenLine = getTestLine();
            try {
                Account account = new Account(path, true);
                account.addLine(givenLine);
                account.addLine(givenLine);
                account.addLine(1, givenLine);
                String firstId = account.getTable().get(1).get(0);
                String secondId = account.getTable().get(2).get(0);
                String thirdId = account.getTable().get(3).get(0);
                assertThat(firstId).isEqualTo("1");
                assertThat(secondId).isEqualTo("2");
                assertThat(thirdId).isEqualTo("3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Nested
    class SaveTableTest {

        @Test
        void saveTable_ifALineIsAddedAndTheFileIsSavedItIsPartOfTheTableAfterReadingAgain() {
            ArrayList<String> givenLine = getTestLine();
            String path = ".\\src\\test\\resources\\testSaveTable.csv";

            try {
                Account account = new Account(path, true);
                account.addLine(givenLine);
                account.saveTable();
                ArrayList<ArrayList<String>> savedTable = account.getTable();
                Account newAccount = new Account(path);
                ArrayList<ArrayList<String>> loadedTable = newAccount.getTable();
                assertThat(savedTable).isEqualTo(loadedTable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Nested
    class RemoveLineTest {
        @Test
        void removeLine_aLineSpecifiedByIdIsRemoved() {
            String path = ".\\src\\test\\resources\\testRemoveLine.csv";
            ArrayList<String> givenLine = getTestLine();
            ArrayList<String> expectedLine = getExpectedLine();
            try {
                Account account = new Account(path, true);
                account.addLine(givenLine);
                account.removeLine(1);
                assertThat(account.getTable()).doesNotContain(expectedLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Test
        void removeLine_IdsAreStillCountingUpAfterALineIsRemoved() {
            String path = ".\\src\\test\\resources\\testRemoveLine.csv";
            ArrayList<String> givenLine = getTestLine();
            try {
                Account account = new Account(path, true);
                account.addLine(givenLine);
                account.addLine(givenLine);
                account.addLine(givenLine);
                account.removeLine(1);
                String firstId = account.getTable().get(1).get(0);
                String secondId = account.getTable().get(2).get(0);
                assertThat(firstId).isEqualTo("1");
                assertThat(secondId).isEqualTo("2");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Test
        void removeLastLine_LastLineOfTheTableIsRemoved() {
            String path = ".\\src\\test\\resources\\testRemoveLine.csv";
            ArrayList<String> givenLine = getTestLine();
            ArrayList<String> expectedLine = getExpectedLine();
            expectedLine.set(0, "3");
            try {
                Account account = new Account(path, true);
                account.addLine(givenLine);
                account.addLine(givenLine);
                account.addLine(givenLine);
                account.removeLastLine();
                assertThat(account.getTable()).doesNotContain(expectedLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<String> getHeader() {
        ArrayList<String> header = new ArrayList<>();
        header.add("ID");
        header.add("Datum");
        header.add("Betrag");
        header.add("Kategorie");
        header.add("Beschreibung");
        return header;
    }

    private ArrayList<String> getTestLine() {
        ArrayList<String> givenLine = new ArrayList<>();
        givenLine.add("220102");
        givenLine.add("50");
        givenLine.add("Einkauf");
        givenLine.add("Wocheneinkauf REWE");
        return givenLine;
    }

    private ArrayList<String> getExpectedLine() {
        ArrayList<String> expectedLine = new ArrayList<>();
        expectedLine.add("1");
        expectedLine.addAll(getTestLine());
        return expectedLine;
    }
}


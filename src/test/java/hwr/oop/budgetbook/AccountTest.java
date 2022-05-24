package hwr.oop.budgetbook;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountTest {
    @Test
    void Account_constructorReadsExistingTable(){
        String path = ".\\src\\test\\resources\\testConstructor.csv";

        try {
            Account account = new Account(path);
            ArrayList<ArrayList<String>> expectedTable = new ArrayList<>();
            ArrayList<String> line1 = new ArrayList<>();
            line1.add("ID");
            line1.add("Datum");
            line1.add("Betrag");
            line1.add("Kategorie");
            line1.add("Beschreibung");
            ArrayList<String> line2 = new ArrayList<>();
            line2.add("1");
            line2.add("220101");
            line2.add("100");
            line2.add("Sonstige Einnahmen");
            line2.add("Zinsen");

            expectedTable.add(line1);
            expectedTable.add(line2);

            assertThat(account.getTable()).isEqualTo(expectedTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addEntry_givenLineIsExpectedToBePartOfTheTable() {
        ArrayList<String> givenLine = getTestLine();
        ArrayList<String> expectedLine = new ArrayList<>();
        expectedLine.add("2");
        expectedLine.add("220102");
        expectedLine.add("50");
        expectedLine.add("Einkauf");
        expectedLine.add("Wocheneinkauf REWE");
        String path = ".\\src\\test\\resources\\testAddEntry.csv";

        try {
            Account account = new Account(path);
            account.addLine(givenLine);
            assertThat(account.getTable()).contains(expectedLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addEntry_IdIsIncremented() {
        ArrayList<String> givenLine = getTestLine();
        String path = ".\\src\\test\\resources\\testAddEntry.csv";

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

    private ArrayList<String> getTestLine() {
        ArrayList<String> givenLine = new ArrayList<>();
        givenLine.add("220102");
        givenLine.add("50");
        givenLine.add("Einkauf");
        givenLine.add("Wocheneinkauf REWE");
        return givenLine;
    }

    @Test
    void saveTable_ifALineIsAddedAndTheFileIsSavedItIsPartOfTheTableAfterReadingAgain() {
        ArrayList<String> givenLine = getTestLine();
        String path = ".\\src\\test\\resources\\saveTable.csv";

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


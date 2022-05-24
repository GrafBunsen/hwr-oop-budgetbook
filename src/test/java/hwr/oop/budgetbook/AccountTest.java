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

    @Test
    void Account_TableIsAppendedOrCreatedEmpty(){
        String path = ".\\src\\test\\resources\\testConstructor.csv";
        ArrayList<ArrayList<String>> expectedTableAppend = new ArrayList<>();
        ArrayList<ArrayList<String>> expectedTableOverride = new ArrayList<>();
        ArrayList<String> line1 = getHeader();
        ArrayList<String> line2 = getExpectedLine();
        expectedTableAppend.add(line1);
        expectedTableAppend.add(line2);
        expectedTableOverride.add(line1);


        try {
            Account appendedAccount = new Account(path,false);
            assertThat(appendedAccount.getTable()).isEqualTo(expectedTableAppend);
            Account overrideAccount = new Account(path,true);
            assertThat(overrideAccount.getTable()).isEqualTo(expectedTableOverride);
        } catch (IOException e) {
            e.printStackTrace();
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


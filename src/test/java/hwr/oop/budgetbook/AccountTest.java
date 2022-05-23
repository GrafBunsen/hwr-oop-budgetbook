package hwr.oop.budgetbook;

import jdk.jfr.Threshold;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;


public class AccountTest {
    @Test
    void Account_constructorReadsExistingTable(){
        String path = ".\\src\\test\\resources\\testConstructor.csv";

        Account test = null;
        try {
            test = new Account(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] expectedTable = new String[][] {{"ID", "Datum", "Betrag", "Kategorie", "Beschreibung"},{"1","220101", "100", "Sonstige Einnahmen", "Zinsen"}};
        assert test != null;
        assertThat(test.getTable()).isEqualTo(expectedTable);
    }

    @Test
    void addEntry_givenLineIsExpectedToBePartOfTheTable(){
        String[] givenLine = new String[]{"2","220102", "50", "Einkauf", "Wocheneinkauf REWE"};
        String path = ".\\src\\test\\resources\\testAddEntry.csv";

        Account account = null;
        try {
            account = new Account(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert account != null;
        account.addEntry(givenLine);
        assertThat(givenLine).isIn((Object) account.getTable());
    }
    @Test
    void saveTable_ifALineIsAddedAndTheFileIsReadAgainItIsPartOfTheTable(){

    }
    @Test
    void addEntry_IdIsIncremented(){
    }
}


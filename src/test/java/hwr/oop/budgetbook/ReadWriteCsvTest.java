package hwr.oop.budgetbook;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.io.IOException;

public class ReadWriteCsvTest {
    @Test
    void readCsvFile_TryIfATestFileIsReadAndDisplayed() {
        String path = ".\\src\\test\\resources\\testReadCsvFile.csv";
        ReadWriteCsv test = new ReadWriteCsv();
        String[][] expectedData = new String[][]{{"ID", "Datum", "Betrag", "Kategorie", "Beschreibung"}, {"1", "220101", "100", "Sonstige Einnahmen", "Zinsen"}};
        String[][] readData = new String[0][];
        try {
            readData = test.readCsvFile(path);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        Assertions.assertThat(readData).isEqualTo(expectedData);
    }

    @Test
    void writeCsvFile_WriteACsvFileWithATestDataset() {
        String[] dataset = new String[]{"1", "220101", "100", "Sonstige Einnahmen", "Zinsen"};
        String path = ".\\src\\test\\resources\\testWriteCvsFile.csv";
        String[][] expectedData = new String[][]{{"ID", "Datum", "Betrag", "Kategorie", "Beschreibung"}, {"1", "220101", "100", "Sonstige Einnahmen", "Zinsen"}};

        ReadWriteCsv test = new ReadWriteCsv();

        try {
            test.writeDataset(dataset, path,true);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        String[][] data = new String[0][];
        try {
            data = test.readCsvFile(path);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        Assertions.assertThat(data).isEqualTo(expectedData);
    }
}

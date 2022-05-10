package hwr.oop.budgetbook;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ReadWriteCsvTest {
    @Test
    void readCsvFile_TryIfATestFileIsReadAndDisplayed() {
        String path = ".\\src\\test\\resources\\test.csv";
        ReadWriteCsv test = new ReadWriteCsv();
        ConsoleOutput output = new ConsoleOutput();
        try {
            String[][] data = test.readCsvFile(path);
            output.printTable(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }

    @Test
    void writeCsvFile_WriteACsvFileWithATestDataset() {
        String[] dataset = new String[]{"1", "220101", "100", "Sonstige Einnahmen", "Zinsen"};
        String path = ".\\src\\test\\resources\\test1.csv";

        ReadWriteCsv test = new ReadWriteCsv();
        ConsoleOutput output = new ConsoleOutput();

        try {
            test.writeDataset(dataset, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[][] data = new String[0][];
        try {
            data = test.readCsvFile(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        output.printTable(data);

    }
}

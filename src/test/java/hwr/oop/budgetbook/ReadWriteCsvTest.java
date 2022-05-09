package hwr.oop.budgetbook;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ReadWriteCsvTest {
    @Test
    void readCsvFile_TryIfATestFileIsReadAndDisplayed() {
        String path = "C:\\Users\\Malte\\IdeaProjects\\hwr-oop-project-bank\\src\\test\\java\\hwr\\oop\\budgetBook\\test.csv";
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
        String[] dataset = new String[]{"doe78", "1234", "John", "Doe"};
        String path = "C:\\Users\\Malte\\IdeaProjects\\hwr-oop-project-bank\\src\\test\\java\\hwr\\oop\\budgetBook\\test1.csv";

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

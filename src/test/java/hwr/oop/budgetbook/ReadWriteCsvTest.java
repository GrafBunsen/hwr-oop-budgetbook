package hwr.oop.budgetbook;

import org.junit.jupiter.api.Test;

public class ReadWriteCsvTest {
    @Test
    void readCsvFile_TryIfATestFileIsReadAndDisplayed() {
        String path = "C:\\Users\\Malte\\IdeaProjects\\hwr-oop-project-bank\\src\\test\\java\\hwr\\oop\\budgetBook\\test.csv";
        ReadWriteCsv test = new ReadWriteCsv();
        try {
            String[][] data = test.readCsvFile(path);
            test.printTable(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }


}

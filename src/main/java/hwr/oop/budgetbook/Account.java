package hwr.oop.budgetbook;

import java.io.*;

public class Account {
    private String[][] table;
    private String path;

    Account(String path) throws IOException {
        boolean doesExist = new File(path).exists();
        this.path = path;

        if (doesExist) {
            table = readCsvFile(path);
        } else {
            table = new String[][]{{"ID", "Datum", "Betrag", "Kategorie", "Beschreibung"}};
        }
    }

    public String[][] getTable() {
        return table;
    }

    public void addEntry(String[] line) {
        HelperClass help = new HelperClass();
        table = help.append(table, line);
    }

    private String[][] readCsvFile(String pathToCsv) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        String row;
        String[][] table = new String[0][0];
        HelperClass helper = new HelperClass();
        while ((row = csvReader.readLine()) != null) {
            String[] line = row.split(",");
            table = helper.append(table, line);
        }
        csvReader.close();
        return table;
    }

    public void saveTable() throws IOException {
        FileWriter csvWriter = new FileWriter(path);

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                csvWriter.append(table[i][j]).append(",");
            }
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

}

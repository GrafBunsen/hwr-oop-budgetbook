package hwr.oop.budgetbook;

import java.io.*;


public class ReadWriteCsv {

    public String[][] readCsvFile(String pathToCsv) throws Exception {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        String row;
        String[][] data = new String[0][0];
        HelperClass helper = new HelperClass();
        while ((row = csvReader.readLine()) != null) {
            String[] line = row.split(",");
            data = helper.append(data, line);
        }
        csvReader.close();
        return data;
    }

    public void writeDataset(String[] dataset, String path, boolean override) throws IOException {
        boolean doesExist = new File(path).exists();
        boolean append = doesExist & !override;
        FileWriter csvWriter = new FileWriter(path, append);
        if (!append) {
            csvWriter.append("ID");
            csvWriter.append(",");
            csvWriter.append("Datum");
            csvWriter.append(",");
            csvWriter.append("Betrag");
            csvWriter.append(",");
            csvWriter.append("Kategorie");
            csvWriter.append(",");
            csvWriter.append("Beschreibung");
        }

        csvWriter.append("\n");

        for (String s : dataset) {
            csvWriter.append(s).append(",");
        }

        csvWriter.flush();
        csvWriter.close();
    }
}

package hwr.oop.budgetbook;

import java.io.*;


public class ReadWriteCsv {

    public String[][] readCsvFile(String pathToCsv) throws Exception {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        String row;
        String[][] data = new String[0][0];
        while ((row = csvReader.readLine()) != null) {
            String[] line = row.split(",");
            data = append(data, line);
        }
        csvReader.close();
        return data;
    }

    public void writeDataset(String[] dataset, String path) throws IOException {
        boolean doesExist = new File(path).exists();
        FileWriter csvWriter = new FileWriter(path, doesExist);
        if (!doesExist) {
            csvWriter.append("Username");
            csvWriter.append(",");
            csvWriter.append("Identifier");
            csvWriter.append(",");
            csvWriter.append("Firstname");
            csvWriter.append(",");
            csvWriter.append("Lastname");
        }

        csvWriter.append("\n");

        for (String s : dataset) {
            csvWriter.append(s).append(",");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    private String[][] append(String[][] oldArray, String[] newLine) {
        int oldArrayLength = oldArray.length;
        int newArrayLength = oldArrayLength + 1;

        String[][] newArray = new String[newArrayLength][];

        if (0 != oldArrayLength) {
            System.arraycopy(oldArray, 0, newArray, 0, oldArrayLength);
        }

        newArray[newArrayLength - 1] = newLine;
        return newArray;
    }
}

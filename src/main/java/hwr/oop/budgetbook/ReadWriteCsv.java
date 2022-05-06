package hwr.oop.budgetbook;

import java.io.*;


public class ReadWriteCsv {

    public String[][] readCsvFile(String pathToCsv) throws Exception {
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        String row;
        String[][] data = new String[0][0];
        while ((row = csvReader.readLine()) != null) {
            String[] line = row.split(";");
            data = append(data, line);
        }
        csvReader.close();
        return data;
    }

    public void writeDataset(String[] dataset,String path) throws IOException {
        boolean doesExist= new File(path).exists();
        FileWriter csvWriter = new FileWriter(path,doesExist);
        if(!doesExist){
            csvWriter.append("Username");
            csvWriter.append(";");
            csvWriter.append("Identifier");
            csvWriter.append(";");
            csvWriter.append("Firstname");
            csvWriter.append(";");
            csvWriter.append("Lastname");
        }

        csvWriter.append("\n");

        for (String s : dataset) {
            csvWriter.append(s).append(";");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    public void printTable(String[][] table){
        int longestCell = getLongestCell(table);
        String[][] output = makeAllCellsTheSameLength(table, longestCell);
        printTheCleanedOutput(output);
    }

    private void printTheCleanedOutput(String[][] output) {
        for (String[] strings : output) {
            System.out.print("| ");
            for (String string : strings) {
                System.out.print(string + " | ");
            }
            System.out.println();
        }
    }

    private String[][] makeAllCellsTheSameLength(String[][] output, int longestCell) {
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                while(output[i][j].length()< longestCell){
                    output[i][j] = output[i][j]+" ";
                }
            }
        }
        return output;
    }

    private int getLongestCell(String[][] table) {
        int longestCell=0;

        for (String[] strings : table) {
            for (String string : strings) {
                if (longestCell < string.length()) {
                    longestCell = string.length();
                }
            }

        }
        return longestCell;
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

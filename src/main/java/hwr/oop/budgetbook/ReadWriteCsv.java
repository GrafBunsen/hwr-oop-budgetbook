package hwr.oop.budgetbook;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;


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

    public void printTable(String[][] table){
        String[][] output = table;
        int[][] cellLength = getLengthOfAllCells(table);
        int longestCell = getLongestCell(cellLength);
        output = makeAllCellsTheSameLength(output, cellLength, longestCell);
        printTheCleanedOutput(output, cellLength);
    }

    private void printTheCleanedOutput(String[][] output, int[][] cellLength) {
        for (int i = 0; i < cellLength.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < cellLength[i].length; j++) {
                System.out.print(output[i][j]+" | ");
            }
            System.out.println();
        }
    }

    private String[][] makeAllCellsTheSameLength(String[][] output, int[][] cellLength, int longestCell) {
        for (int i = 0; i < cellLength.length; i++) {
            for (int j = 0; j < cellLength[i].length; j++) {
                while(output[i][j].length()< longestCell){
                    output[i][j] = output[i][j]+" ";
                }
            }
        }
        return output;
    }

    private int getLongestCell(int[][] cellLength) {
        int longestCell=0;
        for (int[] ints : cellLength) {
            for (int anInt : ints) {
                if (longestCell < anInt) {
                    longestCell = anInt;
                }
            }
        }
        return longestCell;
    }

    private int[][] getLengthOfAllCells(String[][] table) {
        int[][] cellLength = new int[table.length][table[0].length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                cellLength[i][j] = table[i][j].length();
            }
        }
        return cellLength;
    }

    private String @NotNull [] @NotNull [] append(String @NotNull [] @NotNull [] oldArray, String[] newLine) {
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

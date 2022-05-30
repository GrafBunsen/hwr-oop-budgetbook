package hwr.oop.budgetbook;

import java.util.ArrayList;

public class ConsoleOutput {

    public void printTable(ArrayList<ArrayList<String>> table) {
        int longestCell = getLongestCell(table);
        ArrayList<ArrayList<String>> output = makeAllCellsTheSameLength(table, longestCell);
        printTheCleanedOutput(output);
    }

    private int getLongestCell(ArrayList<ArrayList<String>> table) {
        int longestCell = 0;

        for (ArrayList<String> strings : table) {
            for (String string : strings) {
                if (longestCell < string.length()) {
                    longestCell = string.length();
                }
            }

        }
        return longestCell;
    }

    private ArrayList<ArrayList<String>> makeAllCellsTheSameLength(ArrayList<ArrayList<String>> output, int longestCell) {
        for (ArrayList<String> strings : output) {
            for (int j = 0; j < strings.size(); j++) {
                while (strings.get(j).length() < longestCell) {
                    strings.set(j, strings.get(j) + " ");
                }
            }
        }
        return output;
    }

    private void printTheCleanedOutput(ArrayList<ArrayList<String>> output) {
        for (ArrayList<String> strings : output) {
            System.out.print("| ");
            for (String string : strings) {
                System.out.print(string + " | ");
            }
            System.out.println();
        }
    }
}

package hwr.oop.budgetbook;

public class ConsoleOutput {

    public void printTable(String[][] table) {
        int longestCell = getLongestCell(table);
        String[][] output = makeAllCellsTheSameLength(table, longestCell);
        printTheCleanedOutput(output);
    }

    private int getLongestCell(String[][] table) {
        int longestCell = 0;

        for (String[] strings : table) {
            for (String string : strings) {
                if (longestCell < string.length()) {
                    longestCell = string.length();
                }
            }

        }
        return longestCell;
    }

    private String[][] makeAllCellsTheSameLength(String[][] output, int longestCell) {
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                while (output[i][j].length() < longestCell) {
                    output[i][j] = output[i][j] + " ";
                }
            }
        }
        return output;
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
}

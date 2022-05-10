package hwr.oop.budgetbook;

public class HelperClass {

    public String[][] append(String[][] oldArray, String[] newLine) {
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

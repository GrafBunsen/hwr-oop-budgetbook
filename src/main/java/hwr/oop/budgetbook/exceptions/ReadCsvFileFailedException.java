package hwr.oop.budgetbook.exceptions;

public class ReadCsvFileFailedException extends RuntimeException {
    public ReadCsvFileFailedException(String errorMessage) {
        super(errorMessage);
    }

}

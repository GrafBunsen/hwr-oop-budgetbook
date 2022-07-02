package hwr.oop.budgetbook;

public class ReadCsvFileFailedException extends RuntimeException {
    public ReadCsvFileFailedException(String errorMessage) {
        super(errorMessage);
    }

}

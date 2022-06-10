package hwr.oop.budgetbook;

public class InvalidLineException extends RuntimeException {
    public InvalidLineException(String errorMessage) {
        super(errorMessage);
    }
}

package hwr.oop.budgetbook.exceptions;

public class InvalidLineException extends RuntimeException {
    public InvalidLineException(String errorMessage) {
        super(errorMessage);
    }
}

package hwr.oop.budgetbook.exceptions;

public class CouldNotVerifyException extends RuntimeException {
    public CouldNotVerifyException(String errorMessage) {
        super(errorMessage);
    }
}

package hwr.oop.budgetbook.exceptions;

public class SaveTableFailedException extends RuntimeException {
    public SaveTableFailedException(String errorMessage) {
        super(errorMessage);
    }
}

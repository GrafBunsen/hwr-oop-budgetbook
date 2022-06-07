package hwr.oop.budgetbook;

public class SaveTableFailedException extends RuntimeException {
    public SaveTableFailedException(String errorMessage) {
        super(errorMessage);
    }
}

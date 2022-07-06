package hwr.oop.budgetbook.exceptions;

public class HeaderLineNotFoundException extends RuntimeException {
    public HeaderLineNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

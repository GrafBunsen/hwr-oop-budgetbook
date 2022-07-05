package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.view.Account;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


public class ExpensesTest {
    @Test
    public void getAccounts_unknownCategoryGiven_returnNull(){
        Expenses expenses = new Expenses();
        List<String> testEntry = getTestEntry();
        expenses.addTransaction(testEntry);

        Account categoryAccount = expenses.getCategoryAccount("Test");

        assertThat(categoryAccount).isEqualTo(null);
    }

    private List<String> getTestEntry() {
        List<String> givenLine = new ArrayList<>();
        givenLine.add("220102");
        givenLine.add("50");
        givenLine.add("Einkauf");
        givenLine.add("Wocheneinkauf REWE");
        return givenLine;
    }
}

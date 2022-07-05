package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Transaction;
import hwr.oop.budgetbook.view.Account;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ExpensesTest {
    @Test
    public void getAccounts_unknownCategoryGiven_returnNull() {
        Expenses expenses = new Expenses();
        Transaction testTransaction = getTestTransaction();
        expenses.addTransaction(testTransaction);

        Account categoryAccount = expenses.getCategoryAccount("Test");

        assertThat(categoryAccount).isEqualTo(null);
    }

    private Transaction getTestTransaction() {
        return new Transaction(220102, 50, "Einkauf", "Wocheneinkauf REWE");
    }
}

package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Transaction;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class ExpensesTest {
    @Test
    public void getCategoryAccount_unknownCategoryGiven_returnNull() {
        Expenses expenses = new Expenses();
        Transaction testTransaction = getTestTransaction();
        expenses.addTransaction(testTransaction);

        Account categoryAccount = expenses.getCategoryAccount("Test");

        assertThat(categoryAccount).isEqualTo(null);
    }

    @Test
    public void getAccounts_() {
        Expenses expenses = new Expenses();
        expenses.addTransaction(getTestTransaction());

        Map<String, Account> accounts = expenses.getAccounts();

        Account expectedAccount = new Account("Einkauf");
        Transaction expectedTransaction = getTestTransaction();
        expectedTransaction.setAmount(50);
        expectedAccount.addEntry(expectedTransaction);
        Map<String, Account> expectedAccounts = new HashMap<>();
        expectedAccounts.put("Einkauf", expectedAccount);

        assertThat(accounts).isEqualTo(expectedAccounts);
    }

    @Test
    void equals_sameObject_isTrue() {
        Expenses expenses = new Expenses();
        assertThat(expenses).isEqualTo(expenses);
    }

    @Test
    void equals_nullObject_isFalse() {
        Expenses expenses = new Expenses();
        assertThat(expenses).isNotEqualTo(null);
    }

    @Test
    void equals_differentKindOfObject_isFalse() {
        Expenses expenses = new Expenses();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        assertThat(expenses).isNotEqualTo(doubleEntryBookkeepingAccount);
    }

    @Test
    void equals_equalObject_isTrue() {
        Expenses expenses = new Expenses();
        Expenses equalExpenses = new Expenses();
        assertThat(expenses).isEqualTo(equalExpenses);
    }

    private Transaction getTestTransaction() {
        return new Transaction(220102, -50, "Einkauf", "Wocheneinkauf REWE");
    }
}

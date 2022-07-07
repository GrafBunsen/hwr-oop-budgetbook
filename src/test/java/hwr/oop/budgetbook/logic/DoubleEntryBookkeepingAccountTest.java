package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DoubleEntryBookkeepingAccountTest {
    @Test
    public void addTransaction_entryIsInExpenses_isTrue() {
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Entry expectedEntry = getExpectedExpenseEntry();
        Map<Integer, Entry> accountInWhichEntryIsToBeFound = doubleEntryBookkeepingAccount.getExpenseCategoryAccount(expectedEntry.getCategory()).getTable();

        assertThat(accountInWhichEntryIsToBeFound).containsValue(expectedEntry);
    }

    @Test
    public void addTransaction_entryIsInExpenseNegated_isTrue() {
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Entry expectedEntry = getExpectedEntry();
        expectedEntry.setAmount(50);

        Map<Integer, Entry> expectedAccountTable = doubleEntryBookkeepingAccount.getExpenseCategoryAccount(testTransaction.getCategory()).getTable();

        assertThat(expectedAccountTable).containsValue(expectedEntry);
    }

    @Test
    public void addTransaction_entryIsMadeInNewCategory_isTrue() {
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Entry expectedEntry = getExpectedExpenseEntry();

        assertThat(doubleEntryBookkeepingAccount.getExpenseCategoryAccount("Einkauf").getTable()).containsValue(expectedEntry);
    }

    @Test
    public void addTransaction_entryIsMadeInExistingCategory_isTrue() {
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Transaction differentTestTransactionInSameCategory = new Transaction(220202, -100, "Einkauf", "Wocheneinkauf Lidl");
        doubleEntryBookkeepingAccount.addTransaction(differentTestTransactionInSameCategory);

        Entry expectedFirstEntry = new Entry(1, 220102, 50, "Einkauf", "Wocheneinkauf REWE");
        Entry expectedSecondEntry = new Entry(2, 220202, 100, "Einkauf", "Wocheneinkauf Lidl");

        Map<Integer, Entry> categoryAccountTable = doubleEntryBookkeepingAccount.getExpenseCategoryAccount("Einkauf").getTable();

        assertThat(categoryAccountTable).containsValues(expectedFirstEntry, expectedSecondEntry);
    }

    @Test
    public void isVerified_differenceBetweenIncomeAndExpensesIsZero_isTrue() {
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        assertThat(doubleEntryBookkeepingAccount.isVerified()).isTrue();
    }

    @Test
    public void getCategoryAccount_tryingToFindANonexistentCategory_returnsNull() {
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        Account account = doubleEntryBookkeepingAccount.getExpenseCategoryAccount("Test");

        assertThat(account).isEqualTo(null);
    }

    @Test
    void equals_sameObject_isTrue() {
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        assertThat(doubleEntryBookkeepingAccount).isEqualTo(doubleEntryBookkeepingAccount);
    }

    @Test
    void equals_nullObject_isFalse() {
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        assertThat(doubleEntryBookkeepingAccount).isNotEqualTo(null);
    }

    @Test
    void equals_differentKindOfObject_isFalse() {
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        Account account = new Account("Test");
        assertThat(doubleEntryBookkeepingAccount).isNotEqualTo(account);
    }

    @Test
    void equals_equalObject_isTrue() {
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        DoubleEntryBookkeepingAccount equaldoubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        assertThat(doubleEntryBookkeepingAccount).isEqualTo(equaldoubleEntryBookkeepingAccount);
    }

    private Transaction getTestTransaction() {
        return new Transaction(220102, -50, "Einkauf", "Wocheneinkauf REWE");
    }

    private Entry getExpectedEntry() {
        return new Entry(1, getTestTransaction());
    }

    private Entry getExpectedExpenseEntry() {
        return new Entry(1, 220102, 50, "Einkauf", "Wocheneinkauf REWE");
    }
}

package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DoubleEntryBookkeepingAccountTest {
    private Transaction getTestTransaction() {
        return new Transaction(220102, 50, "Einkauf", "Wocheneinkauf REWE");
    }

    private Entry getExpectedEntry(int id) {
        return new Entry(id, getTestTransaction());
    }

    @Test
    public void addTransaction_entryIsInExpenses_isTrue() {
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Entry expectedEntry = getExpectedEntry(1);
        Map<Integer, Entry> accountInWhichEntryIsToBeFound = doubleEntryBookkeepingAccount.getExpenseCategoryAccount(expectedEntry.getCategory()).getTable();

        assertThat(accountInWhichEntryIsToBeFound).containsValue(expectedEntry);
    }

    @Test
    public void addTransaction_entryIsInIncomeNegated_isTrue() {
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Entry expectedEntry = getExpectedEntry(1);
        expectedEntry.setAmount(-50);

        assertThat(doubleEntryBookkeepingAccount.getIncome().getTable()).containsValue(expectedEntry);
    }

    @Test
    public void addTransaction_entryIsMadeInNewCategory_isTrue() {
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Entry expectedEntry = getExpectedEntry(1);

        assertThat(doubleEntryBookkeepingAccount.getExpenseCategoryAccount("Einkauf").getTable()).containsValue(expectedEntry);
    }

    @Test
    public void addTransaction_entryIsMadeInExistingCategory_isTrue() {
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Transaction differentTestTransactionInSameCategory = new Transaction(testTransaction);
        differentTestTransactionInSameCategory.setDate(220202);
        differentTestTransactionInSameCategory.setAmount(100);
        differentTestTransactionInSameCategory.setDescription("Wocheneinkauf Lidl");
        doubleEntryBookkeepingAccount.addTransaction(differentTestTransactionInSameCategory);

        Entry expectedFirstEntry = new Entry(1, testTransaction);
        Entry expectedSecondEntry = new Entry(2, differentTestTransactionInSameCategory);

        Map<Integer, Entry> categoryAccountTable = doubleEntryBookkeepingAccount.getExpenseCategoryAccount("Einkauf").getTable();

        assertThat(categoryAccountTable).containsValues(expectedFirstEntry, expectedSecondEntry);
    }

    @Test
    public void removeTransaction_TransactionSpecified_isRemoved() {
        Transaction testTransaction = getTestTransaction();
        Transaction differentTransaction = getTestTransaction();
        differentTransaction.setAmount(99);

        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);
        doubleEntryBookkeepingAccount.addTransaction(differentTransaction);
        doubleEntryBookkeepingAccount.addTransaction(differentTransaction);

        doubleEntryBookkeepingAccount.removeTransaction(testTransaction);

        Entry expectedExpenseEntry = getExpectedEntry(1);
        Entry expectedIncomeEntry = getExpectedEntry(1);
        expectedIncomeEntry.setAmount(-1* expectedIncomeEntry.getAmount());
        Account expensesAccount = doubleEntryBookkeepingAccount.getExpenseCategoryAccount(testTransaction.getCategory());
        Map<Integer,Entry> incomeAccountTable = doubleEntryBookkeepingAccount.getIncome().getTable();

        assertThat(expensesAccount.getTable()).doesNotContainValue(expectedExpenseEntry);
        assertThat(incomeAccountTable).doesNotContainValue(expectedIncomeEntry);

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
}

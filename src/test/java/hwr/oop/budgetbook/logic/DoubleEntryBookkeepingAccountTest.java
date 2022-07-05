package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;
import hwr.oop.budgetbook.view.Account;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class DoubleEntryBookkeepingAccountTest {
    private Transaction getTestTransaction() {
        return new Transaction(220102,50,"Einkauf","Wocheneinkauf REWE");
    }

    private Entry getExpectedEntry(int id) {
        return new Entry(id,getTestTransaction());
    }

    @Test
    public void addTransaction_entryIsInExpenses_isTrue() {
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Entry expectedEntry = getExpectedEntry(1);

        List<Entry> tableOfExpenses = doubleEntryBookkeepingAccount.getExpenses().getAccounts().get(0).getTable();

        assertThat(expectedEntry).isIn(tableOfExpenses);
    }

    @Test
    public void addTransaction_entryIsInIncomeNegated_isTrue() {
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Entry expectedEntry = getExpectedEntry(1);
        expectedEntry.setAmount(-50);

        assertThat(expectedEntry).isIn(doubleEntryBookkeepingAccount.getIncome().getTable());
    }

    @Test
    public void addTransaction_entryIsMadeInNewCategory_isTrue() {
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Entry expectedEntry = getExpectedEntry(1);

        assertThat(expectedEntry).isIn(doubleEntryBookkeepingAccount.getExpenseCategoryAccount("Einkauf").getTable());
    }

    @Test
    public void addTransaction_entryIsMadeInExistingCategory_isTrue(){
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        Transaction differentTestTransactionInSameCategory = new Transaction(testTransaction);
        differentTestTransactionInSameCategory.setDate(220202);
        differentTestTransactionInSameCategory.setAmount(100);
        differentTestTransactionInSameCategory.setDescription("Wocheneinkauf Lidl");
        doubleEntryBookkeepingAccount.addTransaction(differentTestTransactionInSameCategory);

        Entry expectedFirstEntry = new Entry(1,testTransaction);
        Entry expectedSecondEntry = new Entry(2,differentTestTransactionInSameCategory);

        List<Entry> categoryAccountTable = doubleEntryBookkeepingAccount.getExpenseCategoryAccount("Einkauf").getTable();
        boolean firstEntryIsInCategoryAccount = false;
        boolean secondEntryIsInCategoryAccount = false;
        for (Entry currentEntry : categoryAccountTable) {
            if (currentEntry.equals(expectedFirstEntry)) {
                firstEntryIsInCategoryAccount = true;
            }
            if (currentEntry.equals(expectedSecondEntry)) {
                secondEntryIsInCategoryAccount = true;
            }
        }

        assertThat(firstEntryIsInCategoryAccount && secondEntryIsInCategoryAccount).isTrue();
    }

    @Test
    public void isVerified_differenceBetweenIncomeAndExpensesIsZero_isTrue(){
        Transaction testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        assertThat(doubleEntryBookkeepingAccount.isVerified()).isTrue();
    }

    @Test
    public void getCategoryAccount_tryingToFindANonexistentCategory_returnsNull(){
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        Account account = doubleEntryBookkeepingAccount.getExpenseCategoryAccount("Test");

        assertThat(account).isEqualTo(null);
    }
}

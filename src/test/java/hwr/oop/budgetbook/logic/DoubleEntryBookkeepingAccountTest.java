package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.view.Account;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DoubleEntryBookkeepingAccountTest {
    private List<String> getTestTransaction() {
        List<String> givenLine = new ArrayList<>();
        givenLine.add("220102");
        givenLine.add("50");
        givenLine.add("Einkauf");
        givenLine.add("Wocheneinkauf REWE");
        return givenLine;
    }

    @Test
    public void addTransaction_entryIsInExpenses_isTrue() {
        List<String> testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        List<String> expectedOutput = new ArrayList<>(testTransaction);
        expectedOutput.add(0, "1");

        List<List<String>>tableOfExpenses = doubleEntryBookkeepingAccount.getExpenses().getAccounts().get(0).getTable();

        assertThat(expectedOutput).isIn(tableOfExpenses);
    }

    @Test
    public void addTransaction_entryIsInIncomeNegated_isTrue() {
        List<String> testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        List<String> expectedOutput = new ArrayList<>(testTransaction);
        expectedOutput.add(0, "1");
        expectedOutput.set(2, "-50");

        assertThat(expectedOutput).isIn(doubleEntryBookkeepingAccount.getIncome().getTable());
    }

    @Test
    public void addTransaction_entryIsMadeInNewCategory_isTrue() {
        List<String> testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        List<String> expectedOutput = new ArrayList<>(testTransaction);
        expectedOutput.add(0, "1");

        assertThat(expectedOutput).isIn(doubleEntryBookkeepingAccount.getExpenseCategoryAccount("Einkauf").getTable());
    }

    @Test
    public void addTransaction_entryIsMadeInExistingCategory_isTrue(){
        List<String> testTransaction = getTestTransaction();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        doubleEntryBookkeepingAccount.addTransaction(testTransaction);

        List<String> differentTestTransactionInSameCategory = new ArrayList<>(testTransaction);
        differentTestTransactionInSameCategory.set(0,"220202");
        differentTestTransactionInSameCategory.set(1,"100");
        differentTestTransactionInSameCategory.set(3,"Wocheneinkauf Lidl");
        doubleEntryBookkeepingAccount.addTransaction(differentTestTransactionInSameCategory);

        List<String> expectedFirstLine = new ArrayList<>(testTransaction);
        expectedFirstLine.add(0, "1");

        List<String> expectedSecondLine = new ArrayList<>(differentTestTransactionInSameCategory);
        expectedSecondLine.add(0, "2");

        assertThat(doubleEntryBookkeepingAccount.getExpenseCategoryAccount("Einkauf").getTable()).contains(expectedFirstLine).contains(expectedSecondLine);
    }

    @Test
    public void isVerified_differenceBetweenIncomeAndExpensesIsZero_isTrue(){
        List<String> testTransaction = getTestTransaction();
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

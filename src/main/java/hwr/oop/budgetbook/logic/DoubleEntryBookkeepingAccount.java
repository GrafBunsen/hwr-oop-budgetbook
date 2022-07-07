package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Transaction;

import java.util.Objects;

public class DoubleEntryBookkeepingAccount {
    private final Income income;
    private final Expenses expenses;

    public DoubleEntryBookkeepingAccount() {
        income = new Income();
        expenses = new Expenses();
    }

    public void addTransaction(Transaction transaction) {
        expenses.addTransaction(transaction);
        income.addTransaction(transaction);
    }

    public Income getIncome() {
        return income;
    }

    public Account getExpenseCategoryAccount(String category) {
        return expenses.getCategoryAccount(category);
    }

    public boolean isVerified() {
        int sumOfExpenses = expenses.calculateSumOfExpenses();
        int sumOfIncome = income.calculateSumOfIncome();

        int sum = sumOfIncome + sumOfExpenses;
        return sum == 0;
    }
    
    public void removeTransaction(Transaction transaction) {
        expenses.removeTransaction(transaction);
        income.removeEntry(transaction);
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = (DoubleEntryBookkeepingAccount) o;
        // field comparison
        return Objects.equals(income, doubleEntryBookkeepingAccount.income)
                && Objects.equals(expenses, doubleEntryBookkeepingAccount.expenses);
    }
}

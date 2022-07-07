package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Transaction;

public class DoubleEntryBookkeepingAccount {
    private final Income income;
    private final Expenses expenses;

    DoubleEntryBookkeepingAccount() {
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
}

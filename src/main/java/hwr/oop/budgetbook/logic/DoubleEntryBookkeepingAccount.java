package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.view.Account;

import java.util.ArrayList;
import java.util.List;

public class DoubleEntryBookkeepingAccount {
    private final Income income;
    private final Expenses expenses;

    DoubleEntryBookkeepingAccount() {
        income = new Income();
        expenses = new Expenses();
    }

    public void addTransaction(List<String> entry) {
        expenses.addTransaction(entry);
        income.addTransaction(entry);
    }

    public Expenses getExpenses() {
        return expenses;
    }

    public Income getIncome() {
        return income;
    }

    public Account getExpenseCategoryAccount(String category) {
        return expenses.getCategoryAccount(category);
    }

    public boolean isVerified() {
        int sumOfExpenses=expenses.calculateSumOfExpenses();
        int sumOfIncome=income.calculateSumOfIncome();

        int sum = sumOfIncome+sumOfExpenses;
        return sum == 0;
    }
}

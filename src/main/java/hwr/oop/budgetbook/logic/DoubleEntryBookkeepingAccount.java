package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.view.Account;

import java.util.ArrayList;
import java.util.List;

public class DoubleEntryBookkeepingAccount {
    private final List<Account> income;
    private final List<Account> expenses;

    DoubleEntryBookkeepingAccount() {
        income = new ArrayList<>();
        expenses = new ArrayList<>();
    }

    public void addTransaction(List<String> entry) {
        String category = entry.get(2);

        boolean categoryAccountExists = false;
        int categoryAccountIndex = -1;
        for (int i = 0; i < expenses.size(); i++) {
            if (category.equals(expenses.get(i).getPath())) {
                categoryAccountExists = true;
                categoryAccountIndex = i;
            }
        }

        Account expensesAccount;
        if (categoryAccountExists) {
            expensesAccount = expenses.get(categoryAccountIndex);
        } else {
            expensesAccount = new Account(category);
        }


        Account incomeAccount;
        if (income.isEmpty()) {
            incomeAccount = new Account("balance");
        } else {
            incomeAccount = income.get(0);
        }

        List<String> expensesEntry = new ArrayList<>(entry);
        expensesAccount.addLine(expensesEntry);
        List<String> incomeEntry = new ArrayList<>(entry);
        int amount = Integer.parseInt(entry.get(1)) * -1;
        incomeEntry.set(1, Integer.toString(amount));
        incomeAccount.addLine(incomeEntry);

        if (categoryAccountExists) {
            expenses.set(categoryAccountIndex, expensesAccount);
        } else {
            expenses.add(expensesAccount);
        }

        if (income.isEmpty()) {
            income.add(incomeAccount);
        } else {
            income.set(0, incomeAccount);
        }
    }

    public List<Account> getExpenses() {
        return expenses;
    }

    public List<Account> getIncome() {
        return income;
    }

    public Account getCategoryAccount(String category) {
        for (Account account : expenses) {
            if (account.getPath().equals(category)) {
                return account;
            }
        }
        return null;
    }

    public boolean isVerified() {
        int sumOfExpenses=0;
        int sumOfIncome=0;

        for (Account expens : expenses) {
            sumOfExpenses = expens.sumOverAllEntries();
        }

        for (Account account : income) {
            sumOfIncome += account.sumOverAllEntries();
        }

        int sum = sumOfIncome+sumOfExpenses;
        return sum == 0;
    }
}

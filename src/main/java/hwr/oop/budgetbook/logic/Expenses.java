package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Transaction;
import hwr.oop.budgetbook.view.Account;

import java.util.HashMap;
import java.util.Map;

public class Expenses {
    Map<String, Account> accounts;

    public Expenses() {
        accounts = new HashMap<>();
    }

    public void addTransaction(Transaction transaction) {
        String category = transaction.getCategory();

        boolean categoryAccountExists = accounts.containsKey(category);

        Account expensesAccount;
        if (categoryAccountExists) {
            expensesAccount = accounts.get(category);
        } else {
            expensesAccount = new Account(category);
        }

        expensesAccount.addEntry(transaction);

        accounts.put(category, expensesAccount);
    }

    public int calculateSumOfExpenses() {
        int sumOfExpenses = 0;
        for (String key : accounts.keySet()) {
            sumOfExpenses = accounts.get(key).sumOverAllEntries();
        }
        return sumOfExpenses;
    }

    public Account getCategoryAccount(String category) {
        if (accounts.containsKey(category)) {
            return accounts.get(category);
        }
        return null;
    }
}

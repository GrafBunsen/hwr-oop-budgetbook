package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Expenses {
    final Map<String, Account> accounts;

    public Expenses() {
        accounts = new HashMap<>();
    }

    public void addTransaction(Transaction transaction) {
        String category = transaction.getCategory();

        boolean categoryAccountExists = accounts.containsKey(category);

        Transaction expenseTransaction = new Transaction(transaction);
        int amount = transaction.getAmount() * -1;
        expenseTransaction.setAmount(amount);

        Account expensesAccount;
        if (categoryAccountExists) {
            expensesAccount = accounts.get(category);
        } else {
            expensesAccount = new Account(category);
        }

        expensesAccount.addEntry(expenseTransaction);
        accounts.put(category, expensesAccount);
    }

    public void removeTransaction(Transaction transaction) {
        String category = transaction.getCategory();

        Account account = accounts.get(category);
        account.removeEntry(transaction);
    }

    public int calculateSumOfExpenses() {
        int sumOfExpenses = 0;
        for (String key : accounts.keySet()) {
            sumOfExpenses = accounts.get(key).sumOverAllEntries();
        }
        return sumOfExpenses;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public Account getCategoryAccount(String category) {
        if (accounts.containsKey(category)) {
            return accounts.get(category);
        }
        return null;
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
        Expenses expenses = (Expenses) o;
        // field comparison
        return Objects.equals(accounts, expenses.accounts);
    }
}

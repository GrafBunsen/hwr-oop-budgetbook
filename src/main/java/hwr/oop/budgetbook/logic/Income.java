package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;
import hwr.oop.budgetbook.view.Account;

import java.util.ArrayList;
import java.util.List;

public class Income {

    Account balance;

    public void addTransaction(Transaction transaction) {
        if (balance == null) {
            balance = new Account("balance");
        }

        Transaction incomeTransaction = new Transaction(transaction);
        int amount = transaction.getAmount() * -1;
        incomeTransaction.setAmount(amount);

        balance.addEntry(incomeTransaction);
    }

    public List<Entry> getTable() {
        return balance.getTable();
    }

    public int calculateSumOfIncome() {
        return balance.sumOverAllEntries();
    }
}

package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;

import java.util.Map;
import java.util.Objects;

public class Income {

    Account balance;

    public void addTransaction(Transaction transaction) {
        if (balance == null) {
            balance = new Account("balance");
        }

        Transaction incomeTransaction = new Transaction(transaction);

        balance.addEntry(incomeTransaction);
    }

    public Map<Integer, Entry> getTable() {
        return balance.getTable();
    }

    public int calculateSumOfIncome() {
        return balance.sumOverAllEntries();
    }
    
    public void removeEntry(Transaction transaction) {
        transaction.setAmount(transaction.getAmount()*-1);
        balance.removeEntry(transaction);
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
        Income income = (Income) o;
        // field comparison
        return Objects.equals(balance, income.balance);
    }
}

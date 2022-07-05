package hwr.oop.budgetbook.logic;

import hwr.oop.budgetbook.view.Account;

import java.util.ArrayList;
import java.util.List;

public class Income {

    Account balance;

    public void addTransaction(List<String> entry) {
        if (balance == null) {
            balance = new Account("balance");
        }

        List<String> incomeEntry = new ArrayList<>(entry);
        int amount = Integer.parseInt(entry.get(1)) * -1;
        incomeEntry.set(1, Integer.toString(amount));

        balance.addLine(incomeEntry);
    }

    public List<List<String>> getTable() {
        return balance.getTable();
    }

    public int calculateSumOfIncome() {
        return balance.sumOverAllEntries();
    }
}

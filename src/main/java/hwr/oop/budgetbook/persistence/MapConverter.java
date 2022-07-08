package hwr.oop.budgetbook.persistence;

import hwr.oop.budgetbook.logic.DoubleEntryBookkeepingAccount;
import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;

import java.util.Map;

public class MapConverter {
    public Map<Integer, Entry> toSingleMap(DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount) {
        return doubleEntryBookkeepingAccount.getIncome().getTable();
    }

    public DoubleEntryBookkeepingAccount toDoubleEntryBookkeepingAccount(Map<Integer, Entry> singleMap) {
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        for (int i = 1; i <= singleMap.size(); i++) {
            Transaction currentTransaction = new Transaction(singleMap.get(i).getDate(), singleMap.get(i).getAmount(), singleMap.get(i).getCategory(), singleMap.get(i).getDescription());
            doubleEntryBookkeepingAccount.addTransaction(currentTransaction);
        }
        return doubleEntryBookkeepingAccount;
    }
}

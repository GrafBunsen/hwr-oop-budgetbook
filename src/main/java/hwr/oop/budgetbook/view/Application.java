package hwr.oop.budgetbook.view;

import hwr.oop.budgetbook.logic.DoubleEntryBookkeepingAccount;
import hwr.oop.budgetbook.logic.EntryListConverter;
import hwr.oop.budgetbook.models.Entry;
import hwr.oop.budgetbook.models.Transaction;
import hwr.oop.budgetbook.persistence.AccountPersistence;
import hwr.oop.budgetbook.persistence.PersistenceConverter;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Application {
    private final int TERMINAL_LENGTH = 150;
    private final String CSV_PATH = "";
    private final PersistenceConverter persistenceConverter = new PersistenceConverter();
    private final EntryListConverter entryListConverter = new EntryListConverter();
    private DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount;

    public static void main() {
        System.out.println("Application started.");
    }

    public void printMainScreen() {
        System.out.println("=".repeat(TERMINAL_LENGTH));
        System.out.println("Haushaltsbuch by Malte & Martin");
        System.out.println("Ein Java OOP-Projekt an der HWR Berlin");
        System.out.println("Studiengang Dual Informatik 2021");
        printMainScreenNavigationMenu();
        System.out.println("=".repeat(TERMINAL_LENGTH));
    }

    public int createNumberPrompt(String message) {
        try {
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            scanner.close();
            return input;
        } catch (InputMismatchException mismatchException) {
            System.out.println("Diese Eingabe war nicht gültig. Verwenden Sie eine andere oder beenden Sie die Eingabe mit 0");
            return createNumberPrompt(message);
        }
    }

    public String createStringPrompt(String message) {
        try {
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            scanner.close();
            return input;
        } catch (InputMismatchException mismatchException) {
            System.out.println("Diese Eingabe war nicht gültig. Verwenden Sie eine andere oder beenden Sie die Eingabe mit 'exit'");
            return createStringPrompt(message);
        }
    }

    private void printMainScreenNavigationMenu() {
        System.out.println("=".repeat(TERMINAL_LENGTH));
        System.out.println("Geben Sie eine der folgenden Aktionen mit der jeweiligen Nummer ein und bestätigen mit Enter.");
        System.out.println("[1] Neuen Eintrag erstellen");
        System.out.println("[2] Alle Einträge ansehen");
    }

    private List<List<String>> loadData() {
        return AccountPersistence.readCsvFile(CSV_PATH);
    }

    private Map<Integer, Entry> convertDataForUsage(List<List<String>> convertableList) {
        List<List<String>> listWithoutHeader = persistenceConverter.convertForUsage(convertableList);
        return entryListConverter.convertLines(listWithoutHeader);
    }

    private List<List<String>> convertDataForSaving(Map<Integer, Entry> entryList) {
        List<List<String>> rawList = entryListConverter.convertEntries(entryList);
        return persistenceConverter.convertForPersistence(rawList);
    }

    private void saveData(List<List<String>> account) {
        AccountPersistence.saveTable(account, CSV_PATH);
    }

    private void addTransaction(Transaction transaction) {
        doubleEntryBookkeepingAccount.addTransaction(transaction);
    }

    public void removeTransaction(Transaction transaction){
        doubleEntryBookkeepingAccount.removeTransaction(transaction);
    }

    public boolean isVerified(){
        return doubleEntryBookkeepingAccount.isVerified();
    }
}

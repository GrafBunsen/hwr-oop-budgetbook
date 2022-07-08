package hwr.oop.budgetbook.view;

import hwr.oop.budgetbook.logic.DoubleEntryBookkeepingAccount;
import hwr.oop.budgetbook.models.Transaction;
import hwr.oop.budgetbook.persistence.AccountPersistence;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    private final int TERMINAL_LENGTH = 150;
    private final String CSV_PATH = "./src/main/resources/testPersistence.csv";
    final DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount;

    Application(){
        if (new File(CSV_PATH).isFile()) {
            doubleEntryBookkeepingAccount = loadData();
        } else {
            doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        }
    }

    public void main() {
        printMainScreen();
        int menuPoint = 1;
        while (menuPoint != 0) {
            printMainScreenNavigationMenu();
            menuPoint = createNumberPrompt("Eingabe:");
            if (menuPoint == 1) {
                makeTransaction();
            } else if (menuPoint == 2) {
                deleteTransaction();
            } else if (menuPoint == 3) {
                printTransaction();
            } else if (menuPoint == 0) {
                System.out.println("Auf Wiedersehen");
            } else {
                System.out.println("Üngültige Eingabe. Versuchen Sie es erneut.");
            }
        }
        saveData();
    }

    private void printTransaction() {
        System.out.println("Eintrag entfernen:");
        for (String key : doubleEntryBookkeepingAccount.getExpenses().getAccounts().keySet()) {
            ConsoleOutput consoleOutput = new ConsoleOutput();
            consoleOutput.printTable(doubleEntryBookkeepingAccount.getExpenses().getCategoryAccount(key));
        }
    }

    private void deleteTransaction() {
        System.out.println("Eintrag entfernen:");
        Transaction transaction = createTransaction();
        removeTransaction(transaction);
    }

    private void makeTransaction() {
        System.out.println("Eintrag hinzufügen:");
        Transaction transaction = createTransaction();
        addTransaction(transaction);
    }

    private Transaction createTransaction() {
        int date = createNumberPrompt("Geben Sie das Datum ein (yymmdd):");
        int amount = createNumberPrompt("Geben Sie den Betrag ein (positiv für einnahmen/ negativ für Ausgaben):");
        String category = createStringPrompt("Geben Sie die Kategorie des Eintrages ein:");
        String description = createStringPrompt("Geben Sie eine kurze Beschreibung für den Eintrag ein:");
        return new Transaction(date, amount, category, description);
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
        System.out.println("[2] Einen Eintrag entfernen");
        System.out.println("[3] Alle Einträge ansehen");
        System.out.println("[0] Anwendung verlassen");
    }

    private DoubleEntryBookkeepingAccount loadData() {
        AccountPersistence accountPersistence = new AccountPersistence();
        return accountPersistence.readCsvFile(CSV_PATH);
    }

    private void saveData() {
        AccountPersistence accountPersistence = new AccountPersistence();
        accountPersistence.saveDoubleEntryBookKeepingAccount(doubleEntryBookkeepingAccount, CSV_PATH);
    }

    private void addTransaction(Transaction transaction) {
        doubleEntryBookkeepingAccount.addTransaction(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        doubleEntryBookkeepingAccount.removeTransaction(transaction);
    }

    public boolean isVerified() {
        return doubleEntryBookkeepingAccount.isVerified();
    }
}

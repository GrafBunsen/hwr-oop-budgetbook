package hwr.oop.budgetbook.models;

public class Transaction {
    int date;
    int amount;
    String category;
    String description;

    public Transaction(int newDate, int newAmount, String newCategory, String newDescription) {
        date = newDate;
        amount = newAmount;
        category = newCategory;
        description = newDescription;
    }

    public Transaction(Transaction transaction) {
        this(transaction.getDate(), transaction.getAmount(), transaction.getCategory(), transaction.getDescription());
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

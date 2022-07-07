package hwr.oop.budgetbook.models;

import java.util.Objects;

public class Entry {
    private int id;
    private int date;
    private int amount;
    private String category;
    private String description;

    public Entry(int newId, int newDate, int newAmount, String newCategory, String newDescription) {
        id = newId;
        date = newDate;
        amount = newAmount;
        category = newCategory;
        description = newDescription;
    }

    public Entry(int id, Transaction transaction) {
        this(id, transaction.getDate(), transaction.getAmount(), transaction.getCategory(), transaction.getDescription());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        Entry entry = (Entry) o;
        // field comparison
        return Objects.equals(id, entry.id)
                && Objects.equals(date, entry.date)
                && Objects.equals(amount, entry.amount)
                && Objects.equals(category, entry.category)
                && Objects.equals(description, entry.description);
    }


    public boolean isDerivedOf(Transaction transaction) {
        return Objects.equals(date, transaction.date)
                && Objects.equals(amount, transaction.amount)
                && Objects.equals(category, transaction.category)
                && Objects.equals(description, transaction.description);
    }
}

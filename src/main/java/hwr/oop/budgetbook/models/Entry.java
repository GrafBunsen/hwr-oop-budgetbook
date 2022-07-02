package hwr.oop.budgetbook.models;

public class Entry {
    int id;
    int date;
    int amount;
    String category;
    String description;

    public Entry(int newId, int newDate, int newAmount, String newCategory, String newDescription) {
        id = newId;
        date =  newDate;
        amount = newAmount;
        category = newCategory;
        description = newDescription;
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
}

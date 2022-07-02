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
}

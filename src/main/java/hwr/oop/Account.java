package hwr.oop;

class Account {
    double balance;
    String name;
    int customerId;

    Account(String name, int customerId){
        balance = 0;
        this.name = name;
        this.customerId = customerId;
    }

    void deposit(double amount) {
        balance = balance + amount;
    }

    void withdraw(double amount) {
        balance = balance - amount;
    }

    double getBalance() {
        return balance;
    }

}

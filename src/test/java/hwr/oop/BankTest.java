package hwr.oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BankTest {
    @Test
    void deposit_addsAmountToBalance() {
        Account account1 = new Account("John Doe", 1);
        double amount = 500.3;

        account1.deposit(amount);

        Assertions.assertThat(account1.getBalance()).isEqualTo(amount);
    }

    @Test
    void withdraw_takesAmountOutOfAccount(){
        Account account1 = new Account("John Doe", 1);
        account1.deposit(1000.0);
        double amount = 500.3;

        account1.withdraw(amount);

        Assertions.assertThat(account1.getBalance()).isEqualTo(1000-amount);

    }
}

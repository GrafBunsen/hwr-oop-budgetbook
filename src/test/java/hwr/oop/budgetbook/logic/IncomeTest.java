package hwr.oop.budgetbook.logic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IncomeTest {
    @Test
    void equals_sameObject_isTrue() {
        Income income = new Income();
        assertThat(income).isEqualTo(income);
    }

    @Test
    void equals_nullObject_isFalse() {
        Income income = new Income();
        assertThat(income).isNotEqualTo(null);
    }

    @Test
    void equals_differentKindOfObject_isFalse() {
        Income income = new Income();
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        assertThat(income).isNotEqualTo(doubleEntryBookkeepingAccount);
    }

    @Test
    void equals_equalObject_isTrue() {
        Income income = new Income();
        Income equalIncome = new Income();
        assertThat(income).isEqualTo(equalIncome);
    }
}

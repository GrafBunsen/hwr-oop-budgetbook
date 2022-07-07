package hwr.oop.budgetbook.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {
    @Test
    void setDate_DateIsSet_DateMatches() {
        Transaction transaction = getTestTransaction();
        transaction.setDate(980728);
        assertThat(transaction.getDate()).isEqualTo(980728);
    }

    @Test
    void setCategory_CategoryIsSet_CategoryMatches() {
        Transaction transaction = getTestTransaction();
        transaction.setCategory("Test");
        assertThat(transaction.getCategory()).isEqualTo("Test");
    }

    @Test
    void setDescription_DescriptionIsSet_DescriptionMatches() {
        Transaction transaction = getTestTransaction();
        transaction.setDescription("Test");
        assertThat(transaction.getDescription()).isEqualTo("Test");
    }

    private Transaction getTestTransaction() {
        return new Transaction(220102, 50, "Einkauf", "Wocheneinkauf REWE");
    }

}

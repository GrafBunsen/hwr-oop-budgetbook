package hwr.oop.budgetbook.models;

import hwr.oop.budgetbook.logic.DoubleEntryBookkeepingAccount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EntryTest {
    @Test
    void setId_IdIsSet_IdMatches() {
        Entry entry = new Entry(1, getTestTransaction());
        entry.setId(2);
        assertThat(entry.getId()).isEqualTo(2);
    }

    @Test
    void setDate_DateIsSet_DateMatches() {
        Entry entry = new Entry(1, getTestTransaction());
        entry.setDate(980728);
        assertThat(entry.getDate()).isEqualTo(980728);
    }

    @Test
    void setCategory_CategoryIsSet_CategoryMatches() {
        Entry entry = new Entry(1, getTestTransaction());
        entry.setCategory("Test");
        assertThat(entry.getCategory()).isEqualTo("Test");
    }

    @Test
    void setDescription_DescriptionIsSet_DescriptionMatches() {
        Entry entry = new Entry(1, getTestTransaction());
        entry.setDescription("Test");
        assertThat(entry.getDescription()).isEqualTo("Test");
    }

    @Test
    void equals_sameObject_isTrue() {
        Entry entry = new Entry(1, getTestTransaction());
        assertThat(entry).isEqualTo(entry);
    }

    @Test
    void equals_nullObject_isFalse() {
        Entry entry = new Entry(1, getTestTransaction());
        assertThat(entry).isNotEqualTo(null);
    }

    @Test
    void equals_differentKindOfObject_isFalse() {
        Entry entry = new Entry(1, getTestTransaction());
        DoubleEntryBookkeepingAccount doubleEntryBookkeepingAccount = new DoubleEntryBookkeepingAccount();
        assertThat(entry).isNotEqualTo(doubleEntryBookkeepingAccount);
    }

    @Test
    void equals_equalObject_isTrue() {
        Entry entry = new Entry(1, getTestTransaction());
        Entry equalEntry = new Entry(1, getTestTransaction());
        assertThat(entry).isEqualTo(equalEntry);
    }

    private Transaction getTestTransaction() {
        return new Transaction(220102, 50, "Einkauf", "Wocheneinkauf REWE");
    }

}

package hwr.oop.budgetbook.view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ApplicationTest {
    @Test
    void checkOutputOfApplication() {
        Application application = new Application();
        application.printMainScreen();
    }

    @Disabled
    @Test
    void checkIfNumberPromptReturnsCorrectInputValue() {
        Application application = new Application();
        int input = application.createNumberPrompt("Gebe eine Zahl 2 an.");
        Assertions.assertThat(input).isEqualTo(2);
    }

    @Disabled
    @Test
    void checkIfStringPromptReturnsCorrectInputValue() {
        Application application = new Application();
        String input = application.createStringPrompt("Gebe eine Zahl 2 als Wort in ausschließlich kleinbuchstaben an.");
        Assertions.assertThat(input).isEqualTo("zwei");
    }
}

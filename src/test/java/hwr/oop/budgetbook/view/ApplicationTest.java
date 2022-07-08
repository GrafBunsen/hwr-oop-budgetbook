package hwr.oop.budgetbook.view;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(input).isEqualTo(2);
    }

    @Disabled
    @Test
    void checkIfStringPromptReturnsCorrectInputValue() {
        Application application = new Application();
        String input = application.createStringPrompt("Gebe eine Zahl 2 als Wort in ausschließlich kleinbuchstaben an.");
        assertThat(input).isEqualTo("zwei");
    }

    @Disabled
    @Test
    void main_runMainApplication_userCanUseProgram() {
        Application application = new Application();
        application.main();
    }
}

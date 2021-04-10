package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentRegistrationFormTests extends TestBase {
    private WebSteps step = new WebSteps();

    @Test
    @Owner("ebalakina")
    @Feature("Practice form")
    @Story("Student registration")
    @DisplayName("Fill the form and check Results table")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "URL", url = "https://demoqa.com/automation-practice-form")
    public void practiceFormTest() {
        step.openPracticeFromPage();
        step.fillCommonInformation();
        step.setBirthDate();
        step.addSubjects();
        step.addHobbies();
        step.uploadPicture();
        step.fillAddress();
        step.submitForm();
        step.checkResultsTable();
    }

    @Test
    @Disabled
    @Owner("ebalakina")
    @Feature("Practice form")
    @Story("Student registration")
    @DisplayName("Do not fill the form and check Results table")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "URL", url = "https://demoqa.com/automation-practice-form")
    public void failedFormTest() {
        step.openPracticeFromPage();
        step.submitForm();
        step.checkResultsTable();
    }
}

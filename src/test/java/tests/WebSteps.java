package tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {
//    private final String BASE_URL = "https://demoqa.com/automation-practice-form";
//    gradle clean test -Dbase.url="https://demoqa.com/automation-practice-form"
    private final String BASE_URL = System.getProperty("base.url");

    String firstName = "Ivan";
    String lastName = "Ivanov";
    String email = "ivan.ivanov@company.com";
    String gender = "Male";
    String mobilePhone = "1234567890";
    String monthOfBirth = "January";
    String yearOfBirth = "1990";
    String dayOfBirth = "1";
    String[] subjects = new String[]{"Maths", "English", "Computer Science"};
    String[] hobbies = new String[]{"Reading", "Music"};
    String pictureName = "img.jpg";
    String currentAddress = "Rajasthan, Jaipur, Street, 1st";
    String state = "Rajasthan";
    String city = "Jaipur";

    @Step("Open practice form page")
    public void openPracticeFromPage() {
        open(BASE_URL);
    }

    @Step("Fill the common information")
    public void fillCommonInformation() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(String.format("input[value='%s'", gender)).parent().$("label").click();
        $("#userNumber").setValue(mobilePhone);
    }

    @Step("Set the date of birth")
    public void setBirthDate() {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText(monthOfBirth);
        $(".react-datepicker__year-select").selectOptionContainingText(yearOfBirth);
        $(".react-datepicker__month").$(byText(dayOfBirth)).click();
    }

    @Step("Add subjects")
    public void addSubjects() {
        setSubjects(subjects);
    }

    @Step("Add hobbies")
    public void addHobbies() {
        setHobbies(hobbies);
    }

    @Step("Upload picture")
    public void uploadPicture() {
        $("#uploadPicture").uploadFromClasspath(pictureName);
    }

    @Step("Fill address")
    public void fillAddress() {
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
    }

    @Step("Submit the form")
    public void submitForm() {
        $("#submit").click();
    }

    @Step("Check results table")
    public void checkResultsTable() {
        SelenideElement resultsTable = $(".table-responsive");
        resultsTable.$(byText("Student Name")).parent().shouldHave(text(firstName), text(lastName));
        resultsTable.$(byText("Student Email")).parent().shouldHave(text(email));
        resultsTable.$(byText("Gender")).parent().shouldHave(text(gender));
        resultsTable.$(byText("Mobile")).parent().$("td", 1).shouldHave(text(mobilePhone));
        resultsTable.$(byText("Date of Birth")).parent().$("td", 1).shouldHave(text(dayOfBirth), text(monthOfBirth), text(yearOfBirth));

        checkSubjects(resultsTable, subjects);
        checkHobbies(resultsTable, hobbies);

        resultsTable.$(byText("Picture")).parent().$("td", 1).shouldHave(text(pictureName));
        resultsTable.$(byText("Address")).parent().$("td", 1).shouldHave(text(currentAddress));
        resultsTable.$(byText("State and City")).parent().$("td", 1).shouldHave(text(state), text(city));
    }

    private void setSubjects(String[] subjects) {
        for (String subject : subjects) {
            $("#subjectsInput").setValue(subject).pressEnter();
        }
    }

    private void setHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            $("#hobbiesWrapper").$(byText(hobby)).click();
        }
    }

    private void checkSubjects(SelenideElement resultsTable, String[] subjects) {
        for (String subject : subjects) {
            resultsTable.$(byText("Subjects")).parent().$("td", 1).shouldHave(text(subject));
        }
    }

    private void checkHobbies(SelenideElement resultsTable, String[] hobbies) {
        for (String hobby : hobbies) {
            resultsTable.$(byText("Hobbies")).parent().$("td", 1).shouldHave(text(hobby));
        }
    }
}

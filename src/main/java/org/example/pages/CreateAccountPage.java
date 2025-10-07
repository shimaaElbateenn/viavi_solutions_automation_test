package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class CreateAccountPage extends BasePage {

    private String radioButton_mr = "id=>field-id_gender-1";
    private String radioButton_mrs = "id=>field-id_gender-2";
    private String field_firstName = "id=>field-firstname";
    private String field_lasttName = "id=>field-lastname";
    private String field_email = "id=>field-email";
    private String field_password = "id=>field-password";
    private String field_birthDate = "id=>field-birthday";
    private String check_terms = "css=>input[name='psgdpr']";
    private String check_dataPrivacy = "css=>input[name=\"customer_privacy\"]";
    private String btn_submit = "css=>button[type=\"submit\"]";

    public CreateAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void selectGender(String gender) {
        if (Objects.equals(gender, "mrs")) {
            elementClick(radioButton_mrs, "Select gender mrs");
        } else {
            elementClick(radioButton_mr, "Select gender mr");
        }
    }

    public void enterUserData(String gender, String firstName, String lastName, String email, String password, String birthday) {
        switchToFrame(main_iframe, "Switch to main frame");
        selectGender(gender);
        sendData(field_firstName,firstName, "Enter First Name");
        sendData(field_lasttName,lastName, "Enter Last Name");
        sendData(field_email,email, "Enter Email");
        sendData(field_password,password, "Enter Password");
        sendData(field_birthDate,birthday, "Enter Birthday");
        Check(check_terms,"Check Terms and conditions");
        Check(check_dataPrivacy,"Check Data Privacy");
    }

    public HomePage clickSubmit() {
        elementClick(btn_submit, "Click Submit");
        return new HomePage(driver);
    }
}

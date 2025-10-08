package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    String link_signUp = "partiallink=>No account? Create one here";

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public CreateAccountPage ClickSignUp() {
        elementClick(link_signUp,"Click Sign Up");
        return new CreateAccountPage(driver);
    }
}

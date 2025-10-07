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
        switchToFrame(main_iframe, "Switch to frame");
        elementClick(link_signUp,"Click Sign Up");
        switchToDefaultContent();
        return new CreateAccountPage(driver);
    }
}

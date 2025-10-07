package org.example.base;

import org.openqa.selenium.WebDriver;

public class BasePage extends CustomDriver{
    protected WebDriver driver;
    protected String main_iframe = "id=>framelive";

    public BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean verifyTitle(String expectedTitle) {
        return driver.getTitle().equalsIgnoreCase(expectedTitle);
    }
}

package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCartPage extends BasePage {

    private String items_container = "css=>ul.cart-items";

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean verifyAddingItem() {
        return isElementPresent(items_container, "Item Container");
    }
}

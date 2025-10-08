package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.WebDriver;

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

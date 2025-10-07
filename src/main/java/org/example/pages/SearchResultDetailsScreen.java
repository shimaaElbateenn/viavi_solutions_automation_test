package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultDetailsScreen extends BasePage {

    private String img_cover = "css=>img.js-qv-product-cover";
    private String btn_addToCart = "css=>button.add-to-cart";
    private String cart_model = "css=>div.modal.fade.show, div.modal-dialog, [role='dialog'], .cart-modal";
    private String btn_proceedToPayment = "css=>div.modal-content a.btn-primary[href*='cart?action=show']";

    public SearchResultDetailsScreen(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean verifyCoverImage() {
        return isDisplayed(img_cover, "Cover Image");
    }

    public void clickAddToCart() {
        elementClick(btn_addToCart, "Add To Cart");
    }

    public ShoppingCartPage navigateToCart() {
        elementClick(btn_proceedToPayment, "proceed to checkout");
        return new ShoppingCartPage(driver);
    }
}

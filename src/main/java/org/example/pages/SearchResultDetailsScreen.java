package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.WebDriver;

public class SearchResultDetailsScreen extends BasePage {

    private String img_cover = "css=>img.js-qv-product-cover";
    private String btn_addToCart = "css=>button.add-to-cart";
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

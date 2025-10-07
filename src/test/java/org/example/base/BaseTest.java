package org.example.base;

import org.example.pages.*;
import org.example.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
    public WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected CreateAccountPage createAccountPage;
    protected SearchResultDetailsScreen searchResultDetailsScreen;
    protected ShoppingCartPage shoppingCartPage;

    @BeforeClass
    @Parameters({"browser"})
    public void method_setUp(String browser) {
        driver = WebDriverFactory.getInstance().getDriver(browser);
        driver.get(Constants.BASE_URL);
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void driver_tearDown() {
        WebDriverFactory.getInstance().quitDriver();
    }
}

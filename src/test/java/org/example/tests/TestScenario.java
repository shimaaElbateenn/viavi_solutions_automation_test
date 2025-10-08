package org.example.tests;

import org.example.base.BaseTest;
import org.example.models.AccountData;
import org.example.utilities.JsonDataProvider;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestScenario extends BaseTest {

    @BeforeMethod
    public void switchToLoveFrame() {
        homePage.switchToLiveFrame();
    }

    @Test(dataProvider = "jsonAccountData", dataProviderClass = JsonDataProvider.class)
    public void testScenario(AccountData accountData) {
        loginPage = homePage.clickSignIn();
        createAccountPage = loginPage.ClickSignUp();
        createAccountPage.enterUserData(accountData.getGender(), accountData.getFirstName(),
                accountData.getLastName(), accountData.getEmail(),
                accountData.getPassword(), accountData.getBirthday());
        homePage = createAccountPage.clickSubmit();
        homePage.searchFromCatalog("notebook");
        searchResultDetailsScreen = homePage.selectFirstSearchResult();
        Assert.assertTrue(searchResultDetailsScreen.verifyCoverImage());
        searchResultDetailsScreen.clickAddToCart();
        shoppingCartPage = searchResultDetailsScreen.navigateToCart();
        Assert.assertTrue(shoppingCartPage.verifyAddingItem());
        homePage.signOut();
    }

    @AfterMethod
    public void switchToDefaultFrame() {
        homePage.switchToDefaultContent();
    }


}

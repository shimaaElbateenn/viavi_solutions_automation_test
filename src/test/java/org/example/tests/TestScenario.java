package org.example.tests;

import org.example.base.BaseTest;
import org.example.data.AccountData;
import org.example.utilities.JsonDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestScenario extends BaseTest {

    @DataProvider(name = "jsonAccountData")
    public static Object[][] jsonAccountData() throws Exception {
        return JsonDataProvider.getAccountData();
    }

    @Test(dataProvider = "jsonAccountData", dataProviderClass = JsonDataProvider.class)
    public void testScenario(AccountData accountData) {
        loginPage = homePage.clickSignIn();
        createAccountPage = loginPage.ClickSignUp();
        createAccountPage.enterUserData(accountData.getGender(), accountData.getFirstName(),
                accountData.getLastName(), accountData.getEmail(),
                accountData.getPassword(), accountData.getBirthday());
        createAccountPage.clickSubmit();
        homePage.searchFromCatalog("notebook");
        searchResultDetailsScreen = homePage.selectFirstSearchResult();
        Assert.assertTrue(searchResultDetailsScreen.verifyCoverImage());
        searchResultDetailsScreen.clickAddToCart();
        shoppingCartPage = searchResultDetailsScreen.navigateToCart();
        Assert.assertTrue(shoppingCartPage.verifyAddingItem());
    }
}

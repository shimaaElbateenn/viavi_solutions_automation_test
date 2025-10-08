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
        //1. Create an account.
        loginPage = homePage.clickSignIn();
        createAccountPage = loginPage.ClickSignUp();
        createAccountPage.enterUserData(accountData.getGender(), accountData.getFirstName(),
                accountData.getLastName(), accountData.getEmail(),
                accountData.getPassword(), accountData.getBirthday());
        homePage = createAccountPage.clickSubmit();

        //2. From the homepage, search for “notebook.”
        homePage.searchFromCatalog("notebook");

        //3. Select the first search result and assert that it has an image.
        searchResultDetailsScreen = homePage.selectFirstSearchResult();
        Assert.assertTrue(searchResultDetailsScreen.verifyCoverImage());

        //4. Add it to the cart.
        searchResultDetailsScreen.clickAddToCart();

        //5. Navigate to the cart.
        shoppingCartPage = searchResultDetailsScreen.navigateToCart();

        //6. Assert that the product is successfully added.
        Assert.assertTrue(shoppingCartPage.verifyAddingItem());

        homePage.signOut();
    }

    @AfterMethod
    public void switchToDefaultFrame() {
        homePage.switchToDefaultContent();
    }


}

package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    String link_signIn = "link=>Sign in";
    String link_signOut = "class=>logout";
    String search_box = "css=>input[placeholder=\"Search our catalog\"]";
    String first_search_result = "css=>li.ui-menu-item:first-child a";


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public LoginPage clickSignIn() {
        elementClick(link_signIn,"Click Sign In");
        return new LoginPage(driver);
    }

    public void signOut() {
        elementClick(link_signOut,"Click Sign In");
    }

    public void searchFromCatalog(String searchQuery) {
        sendData(search_box, searchQuery, "Search for " + searchQuery);
    }

    public SearchResultDetailsScreen selectFirstSearchResult() {
        elementClick(first_search_result, "First Search result");
        return new SearchResultDetailsScreen(driver);
    }

}

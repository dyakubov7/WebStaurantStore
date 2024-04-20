package webStaruantStore.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import webStaruantStore.pages.SearchPage;
import webStaruantStore.utils.BrowserUtils;

import static webStaruantStore.pages.SearchPage.descriptions;


public class SearchTests extends TestBase{

    SearchPage searchPage;
    @Test(priority = 1)
    public void verifyTitles() throws InterruptedException {
        logger = report.createTest("Verify Titles");
        searchPage = new SearchPage(driver);
        searchPage.searchByText("stainless work table");
        searchPage.navigateToEachPageAndGetTitles();
        for(String eachDescription: descriptions){
            Assert.assertTrue(eachDescription.contains("Table"));
           }

    }

    @Test(priority = 2)
    public void addAndRemoveLastItemFromCart() throws InterruptedException {
        logger = report.createTest("Add and Remove Last Item From Cart");
        searchPage = new SearchPage(driver);
        searchPage.searchByText("stainless work table");
        searchPage.navigateToLastPageFromSearch();
        searchPage.addlastItemToCart();
        BrowserUtils.wait(4);
        searchPage.navigateToCart();
        searchPage.emptyCart();
        BrowserUtils.wait(2);
        Assert.assertEquals("Your cart is empty.",searchPage.getCartIsEmptyMessage());


    }



}

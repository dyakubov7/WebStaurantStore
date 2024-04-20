package webStaruantStore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webStaruantStore.utils.BrowserUtils;
import webStaruantStore.utils.Driver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    public WebDriver driver;
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public static ArrayList<String> descriptions = new ArrayList<>();

    public static String lastOfFoundItem;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }


    private By itemDescriptions = By.xpath("//span[@data-testid='itemDescription']");

    private By searchBox = By.id("searchval");

    private By listOfLinksInPagination = By.xpath("//*[@aria-label = 'pagination']//a");

    private By addToCartButton = By.id("buyButton");

    private By viewCartButton = By.linkText("View Cart");

    private By cartIcon = By.id("cartItemCountSpan");

    private By confirmationEmptyCartButton = By.xpath("//footer[@data-testid = 'modal-footer']/button[1]");

    private By emptyCartButton = By.xpath("//button[text() = 'Empty Cart']");

    private By cartIsEmptyMessage = By.xpath("//p[@class = \"header-1\"]");

    public void searchByText(String searchText) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(searchBox)));
        driver.findElement(searchBox).sendKeys(searchText, Keys.ENTER);
    }

    public int getTheSizeOfThePagination() {
        List<WebElement> list = driver.findElements(listOfLinksInPagination);
        int lastPageBeforeNextButton = list.size() - 1;
        String pageBeforeNextButton = String.valueOf(lastPageBeforeNextButton);
        WebElement elementBeforeNextButton = driver.findElement(By.xpath("//*[@id=\"paging\"]/nav/ul/li[" + pageBeforeNextButton + "]/a"));
        String numberOfPages = elementBeforeNextButton.getText();
        return Integer.parseInt(numberOfPages);
    }

    public void addItemDescriptionsFromSearchToList() {

        for (WebElement description : driver.findElements(itemDescriptions) ) {
            String eachDescription = description.getText();
            descriptions.add(eachDescription);
        }
    }

    public void navigateToEachPageAndGetTitles() throws InterruptedException {
        WebElement pageNum;
        for (int i = 1; i <= getTheSizeOfThePagination(); i++) {
            String pageNumberasString = String.valueOf(i);
            pageNum = driver.findElement(By.linkText(pageNumberasString));
            wait.until(ExpectedConditions.elementToBeClickable(pageNum)).click();
            BrowserUtils.wait(1);
            addItemDescriptionsFromSearchToList();

        }
    }
    public String getLastItemFromList(){
        lastOfFoundItem = descriptions.getLast();
        return lastOfFoundItem;
    }


    public void addlastItemToCart(){
        addItemDescriptionsFromSearchToList();
        getLastItemFromList();
        WebElement lastItem = driver.findElement(By.linkText(lastOfFoundItem));
        wait.until(ExpectedConditions.elementToBeClickable(lastItem)).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(addToCartButton)));
        driver.findElement(addToCartButton).click();
    }

    public void navigateToLastPageFromSearch(){
        driver.findElement(By.linkText(String.valueOf(getTheSizeOfThePagination()))).click();
    }
    public void navigateToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(viewCartButton)));
        driver.findElement(viewCartButton).click();
    }
    public void emptyCart(){
        driver.findElement(emptyCartButton).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(confirmationEmptyCartButton)));
        driver.findElement(confirmationEmptyCartButton).click();
    }

    public String getCartIsEmptyMessage(){
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(cartIsEmptyMessage),"Your cart is empty."));

      return  driver.findElement(cartIsEmptyMessage).getText();
    }



}

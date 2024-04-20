package webStaruantStore.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import webStaruantStore.utils.Driver;

public class TestBase {
    public static WebDriver driver;
    public ExtentReports report;

    public ExtentSparkReporter sparkReporter;

    public ExtentTest logger;

    @BeforeTest
    public void beforeTest(){

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/report2.html");
        report = new ExtentReports();
        report.attachReporter(sparkReporter);
        sparkReporter.config().setReportName("Webstaruant Automation");
    }

    @BeforeMethod
    public void setup(){
        driver = Driver.getChromeDriver();
        driver.get("https://www.webstaurantstore.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test Case Failed", ExtentColor.RED));
        }else
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case Passed", ExtentColor.GREEN));

        Driver.closeDriver();
    }

    @AfterTest
    public void afterTest(){
        report.flush();
    }
}

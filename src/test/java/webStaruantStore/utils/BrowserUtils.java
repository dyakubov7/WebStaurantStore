package webStaruantStore.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserUtils {

    public static void wait(int seconds){
        try{
            Thread.sleep(1000L * seconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    }

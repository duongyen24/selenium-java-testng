package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_20_Wait_PageReady {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //explicitWait = new WebDriverWait(driver,10);
        explicitWait = new WebDriverWait(driver,30);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_orangehr() {

        driver.get("https://opensource-demo.orangehrmlive.com");

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");

        driver.findElement(By.cssSelector("button.oxd-button")).click();

        Assert.assertTrue(isPageLoadingSuccess());
        Assert.assertEquals(driver.findElement(By.cssSelector("p.oxd-text")).getText(),"Time at Work");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isPageLoadingSuccess(){

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return (Boolean) jsExecutor.executeScript("return(window.jQuery != null)&&(jQuery.active ===0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);

    }

    public void switchToWindowFrom(String otherID){

        Set<String> allID = driver.getWindowHandles();

        //dung for loop kiem tra
        // neu khac voi parent window id thi click
        for (String id : allID){
            if(!id.equals(otherID)){
                driver.switchTo().window(id);
                sleepInSecond(2);
            }
        }

    }
}
package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_20_Wait_ImplicitWait {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
      //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //explicitWait = new WebDriverWait(driver,10);
    }

    @Test
    public void TC_01_NotenoughTime() {

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div>button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div>h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_02_EnoughTime(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div>button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div>h4")).getText(),"Hello World!");


    }

    @Test
    public void TC_03_OverTime(){
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div>button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div>h4")).getText(),"Hello World!");


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
package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_20_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver,10);
    }

    @Test
    public void TC_01_Disphayed() {
        //co tren UI - co trong HTML
        driver.get("https://www.facebook.com/");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
    }


    @Test
    public void TC_02_1_Undisphayed(){

        //k hien thi tren UI - hien thi trong DOM
        driver.get("https://www.facebook.com/");

        driver.findElement(By.xpath("//a[text()='Create New Account']")).click();

        //wait thang nay k hien thi trong 10s
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("input[aria-label='Re-enter email address']")));


    }

    @Test
    public void TC_2_2_Undisphayed(){

        //k hien thi tren UI - k hien thi trong DOM
        driver.get("https://www.facebook.com/");

        //wait thang nay k hien thi trong 10s
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("input[aria-label='Re-enter email address']")));


    }

    @Test
    public void TC_3_1_Presence(){

        //co hien thi tren UI - co hien thi trong DOM
        driver.get("https://www.facebook.com/");

        //wait thang nay k hien thi trong 10s
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
    }

    @Test
    public void TC_3_2_Presence(){

        //k hien thi tren UI - hien thi trong DOM
        driver.get("https://www.facebook.com/");

        driver.findElement(By.xpath("//a[text()='Create New Account']")).click();

        //wait thang nay k hien thi trong 10s
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("input[aria-label='Re-enter email address']")));
    }






    @AfterClass
    public void afterClass() {
        //driver.quit();
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
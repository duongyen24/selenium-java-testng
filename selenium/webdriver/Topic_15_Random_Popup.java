package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_15_Random_Popup {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        jsExecutor = (JavascriptExecutor) driver;

    }

    @Test
    public void TC_06() {

        driver.get("http://www.kmplayer.com/");

        WebElement popup = driver.findElement(By.cssSelector("img#support-home"));
        sleepInSecond(3);

        //neu co popup
        if (popup.isDisplayed()){
            //click to element when it is hide using selenium JS
            // "arguments[0].click();"
            System.out.println("go to popup");
            jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("area#btn-r")));
        } else {
            //neu k co popup
            System.out.println("go out popup");
        }

        driver.findElement(By.xpath("//li/a[text()='PC 64X']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='KMPlayer 64X']")).getText(),"KMPlayer 64X");
    }


    @Test
    public void TC_07(){
        driver.get("https://dehieu.vn/");

        List<WebElement> popups = driver.findElements(By.cssSelector("div.popup-content"));

        if (popups.size()>0 && popups.get(0).isDisplayed()){
            System.out.println("popup show");
            driver.findElement(By.cssSelector("button#close-popup")).click();
        } else {
            System.out.println("popup not show");
        }

        driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();





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

    public String randomEmail(){
        Random rand = new Random();
        return "yen" + rand.nextInt(999) + "gmail.com";
    }
}
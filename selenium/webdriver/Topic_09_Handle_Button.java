package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;

import java.util.concurrent.TimeUnit;

public class Topic_09_Handle_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        //verify login button disabled
        Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("yen@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");

        //verify login button enabled
        Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

        //verify css value
        String rgbColor = driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");

        //rgba to hexa color
        String hexaColor = Color.fromString(rgbColor).asHex().toUpperCase();

        //verify
        Assert.assertEquals(hexaColor,"C92127");

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
}
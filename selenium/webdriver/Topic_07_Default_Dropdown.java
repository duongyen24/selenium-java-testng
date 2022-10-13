package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.print.attribute.standard.PrinterMakeAndModel;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07_Default_Dropdown {
    WebDriver driver; //khai bao
    Select select; // khai bao
    Random rand;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver(); // khoi tao
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //khoi tao random
        rand = new Random();
    }

    @Test
    public void TC_01_Default_Dropdown() {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Joe");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Biden");

        //khoi tao select de nhap vao dropdown
        select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
        select.selectByVisibleText("13");

        select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
        select.selectByVisibleText("May");

        select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
        select.selectByVisibleText("1912");

        String emailAddress = "joe" + rand.nextInt(9999) + "@gmail.com";

        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Company")).sendKeys("white house");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
        driver.findElement(By.cssSelector("a.ico-account")).click();

        //verify data in dropdown
        select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"13");

        select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"May");

        select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"1912");


        //verify email
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"),emailAddress);



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
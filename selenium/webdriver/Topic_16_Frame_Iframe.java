package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_Frame_Iframe {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_() {
        driver.get("https://kyna.vn/");

        Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));

        WebElement fbLike =  driver.findElement(By.xpath("//a[text()='Kyna.vn']//parent::div//following-sibling::div"));

        Assert.assertEquals(fbLike.getText(),"165K likes");

        //switch ve main page
        driver.switchTo().defaultContent();

        //switch to chat popup
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));

        driver.findElement(By.cssSelector("div.button_bar")).click();

        driver.findElement(By.cssSelector("input.input_name")).sendKeys("yen");

        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("012345678");
        driver.switchTo().defaultContent();

        sleepInSecond(3);
        driver.findElement(By.xpath("//div[@class='form-inline header-search']//input")).sendKeys("Excel");
        driver.findElement(By.cssSelector("button.search-button")).click();

        List <WebElement> lists = driver.findElements(By.cssSelector("div.content>h4"));

        for (WebElement item : lists){
            System.out.println(item.getText());
        }
    }

    @Test
    public void TC_02(){
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("login_page");

        driver.findElement(By.cssSelector("input.form-control")).sendKeys("yen2022");


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
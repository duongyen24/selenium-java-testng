package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_13_Actions {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        //hover element
        action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover(){
        driver.get("https://www.myntra.com/");

        //hover vao kid menu link
        action.moveToElement(driver.findElement(By.xpath("//header//a[text()='Kids']"))).perform();
        sleepInSecond(3);

        //click vao menu sau khi hover
        action.click(driver.findElement(By.xpath("//header//a[text()='Home & Bath']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(),"Kids Home Bath");
    }

   @Test
   public void TC_03_Hover(){
        driver.get("https://www.fahasa.com/");

        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        sleepInSecond(3);

     //   action.click(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Kỹ Năng Sống']"))).perform();

       driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Kỹ Năng Sống']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='mb-breadcrumbs']//li[@class='category213']")).getText(),"KỸ NĂNG SỐNG");

   }

   @Test
   public void TC_04_Click_and_Hold(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        //thao tac voi 12 so
       List<WebElement> listNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
       System.out.println("tong so luong" + listNumbers.size());

       //click vao so dau tien va giu chuot trai
       //di chuot den so ket thuc
       //nha chuot trai ra
       action.clickAndHold(listNumbers.get(0)).moveToElement(listNumbers.get(9)).release().perform();
       sleepInSecond(3);

       List<WebElement> listNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
       Assert.assertEquals(listNumberSelected.size(),6);

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
package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Locator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_ID() {
        //dau tien phai tim duoc element do: findElement
        //find gi?: id/class/name/css/xpath
        //find roi action gi? click/sendkey
        driver.findElement(By.id("FirstName")).sendKeys("Automate");


    }

    @Test
    public void TC_02_Class(){
        //open Search screen
        driver.get("https://demo.nopcommerce.com/search");

        //input text in Search textbox
        driver.findElement(By.className("search-text")).sendKeys("Macbook");
    }

    @Test
    public void TC_03_Name(){
        //click on Advanced Search checkbox
        driver.findElement(By.name("advs")).click();
    }

    @Test

    public void TC_04_TagName(){
        //tim ra bn the input tren man hinh hien tai
        // noted: findElements
        System.out.println(driver.findElements(By.tagName("input")).size());
    }
    @Test

    public void TC_05_LinkText(){
        //click vao duong link address
        // tim tuyet doi
        driver.findElement(By.linkText("Addresses")).click();
    }
    @Test

    public void TC_06_PartialLinkText(){
        //click vao duong link Appy for vendor account ( tuong doi)
        driver.findElement(By.partialLinkText("vendor account")).click();
    }

    @Test
    public void TC_07_Css(){
        //open Register page
        // css xai #
        driver.get("https://demo.nopcommerce.com/register");

        //1
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");

        //2
        driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");

        //3
        driver.findElement(By.cssSelector("input[id='Email']")).sendKeys("automate");
    }

    @Test

    public void TC_08_XPath(){

        driver.get("https://demo.nopcommerce.com/register");
        //1
        driver.findElement(By.xpath("//input[@data-val-required='First name is required.']")).sendKeys("Selenium");

        //2
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");

        //3
        driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("automate");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
package webdriver.javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Webbrowser_command {
    WebDriver driver; // tuong tac voi trinh duyet
    WebElement element; // tuong tac voi web HTML element

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_WebBrowser_Command() {
        driver.quit();
        WebElement driver1 = driver.findElement(By.cssSelector(""));
        driver.findElements(By.cssSelector(""));
        driver.getPageSource();
        driver.manage().getCookies(); // trong bai framework
        driver.manage().window().maximize(); // mo trinh duyet max screen cho k bi fail
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

        driver.switchTo().alert();
        driver.switchTo().frame(0);

        driver.getTitle();





    }

    @Test
    public void TC2_WebElement_Command(){
        // tuong tac thong qua HTML Element
        // 2 cach:

        // khai bao bien dung lai nhieu lan
        WebElement email = driver.findElement(By.id("email"));
        email.clear();
        email.sendKeys("a@gmail.com");

        //dung truc tiep
        driver.findElement(By.id("email")).clear();

        element.isDisplayed();

        //tra ve gia tri nam trong attribute
        element.getAttribute("placeholder");

        //tim data nam trong value attribute
        driver.findElement(By.id("firstName")).getAttribute("value");

        




    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
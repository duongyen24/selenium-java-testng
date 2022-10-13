package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_Custom_Dropdown {
    WebDriver driver; //khai bao
    Select select; // khai bao
    WebDriverWait explicitWait; //khai boa

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver(); // khoi tao
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //khoi tao wait
        explicitWait = new WebDriverWait(driver,30);

    }

    @Test
    public void TC_01_Custom_Dropdown() {

        //click vao 1 phan tu nao do cho no xo ra
        driver.findElement(By.cssSelector("span#number-button")).click();






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
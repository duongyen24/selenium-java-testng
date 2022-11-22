package webdriver.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_20_Mix_Implicit_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Element_Found() {

        //element co xuat hien va k can het timeout
        //du co set cả 2 loai wait cung k anh huong

        explicitWait = new WebDriverWait(driver,15);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        //implcit wait: chi apply cho find element
        //exphicit cho cac dieu kien khac

        driver.get("https://www.facebook.com/");

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
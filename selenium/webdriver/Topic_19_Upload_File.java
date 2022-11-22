package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_19_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String file1Name1 = "1.png";
    String fileName1Path = projectPath + "/images/"+ file1Name1;



    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void TC_01_() {

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(fileName1Path);
        //driver.findElement(By.cssSelector("input[type='file']")).sendKeys(fileName1Path + "\n" + file2); -> upload multi
        driver.findElement(By.cssSelector("button.start")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("table p a")).isDisplayed());


    }


    @AfterClass
    public void afterClass() {
    //    driver.quit();
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
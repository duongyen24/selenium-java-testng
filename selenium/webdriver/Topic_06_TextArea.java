package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_TextArea {
    WebDriver driver;
    String firstName, lastName, employeeID;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        firstName = "employee";
        lastName = "one";

    }

    @Test
    public void TC_01_Textbox_Textarea() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sleepInSecond(5); //login xong sleep 5s cho stable


        //mo man hinh Employee
        driver.findElement(By.xpath("//div[@class='orangehrm-header-container']/button")).click();

        //input firstname and lastname
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);

         //get gia tri cua employeeID ve roi luu vao bien
        employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']//following::div/input")).getText();

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //verify field is enabled
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='firstName']")).isEnabled());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='lastName']")).isEnabled());
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Employee Id']//following::div/input")).isEnabled());







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
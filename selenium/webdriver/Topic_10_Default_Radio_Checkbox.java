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

public class Topic_10_Default_Radio_Checkbox {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        //select checkbox
        checkCheckboxorRadioButton("//input[@value='Gout']");
        checkCheckboxorRadioButton("//input[@value='Heart Disease']");

        //verify selected
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Gout']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Heart Disease']")).isSelected());

        //select radio button
        driver.findElement(By.xpath("//input[@value='5+ days']")).click();

        //verify selected
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());

        //unselected checkbox
        driver.findElement(By.xpath("//input[@value='Gout']")).click();
        driver.findElement(By.xpath("//input[@value='Heart Disease']")).click();

        //verify unselected
        Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Gout']")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Heart Disease']")).isSelected());

        //select another radio button
        driver.findElement(By.xpath("//input[@value='No']")).click();

        //verify
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='No']")).isSelected());


        //select and check all checkbox
        List<WebElement> selectAll = driver.findElements(By.xpath("//input[@type='checkbox']"));

        for (WebElement checkbox :selectAll){
            if(!checkbox.isSelected()){
                checkbox.click();
                sleepInSecond(1);
            }
        }

        for (WebElement checkbox : selectAll){
            Assert.assertTrue(checkbox.isSelected());
        }
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void checkCheckboxorRadioButton(String xpathLocator){
        if(!driver.findElement(By.xpath(xpathLocator)).isSelected()){
            driver.findElement(By.xpath(xpathLocator)).click();
        }
    }

    //check xem co select hay chua de verify
    public boolean isSelected(String xpathLocator){
        return driver.findElement(By.xpath(xpathLocator)).isSelected();
    }

    public void sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
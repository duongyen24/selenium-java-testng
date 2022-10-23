package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_11_CustomRadioandDropdown {
    WebDriver driver;
    JavascriptExecutor jsExcute;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        jsExcute = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_03_Checkbox() {
        //the input dùng để click nhưng bị ẩn k click được
        //thẻ span clickđược nhưng k dùng verify
        //tìm cách work-around
        //hàm click() k click vào element bị ẩn được
        //hàm click() của jsexcutor để click: k quan tâm element bị ẩn hay k
        By checkedCheckbox = By.xpath("//span[text()='Checked']//preceding-sibling::span/input");
        jsExcute.executeScript("arguments[0].click();",driver.findElement(checkedCheckbox));

        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());

        //code to execute
        //var checkBox = $x("//span[text()='Checked']//preceding-sibling::span/input")[0];
        //checkBox.click();
    }

    @Test
    public void TC_03_Radio(){
        driver.get("https://material.angular.io/components/radio/examples");
       // By radioButton = By.xpath("//span[contains(text(),'Spring')]//preceding-sibling::span/input");
        By radioButton = By.xpath("//input[@value='Spring']");

        jsExcute.executeScript("arguments[0].click();",driver.findElement(radioButton));
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(radioButton).isSelected());
    }

    @Test
    public void TC_04_Google_doc(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By canTho = By.xpath("//div[@aria-label='Cần Thơ']");

        ///verify truoc khi click
        Assert.assertEquals(driver.findElement(canTho).getAttribute("aria-checked"),"false");

        //click vao
        checktoCheckboxandRadio("//div[@aria-label='Cần Thơ']");

        //verify da click
        Assert.assertEquals(driver.findElement(canTho).getAttribute("aria-checked"),"true");

    }

    public void checktoCheckboxandRadio(String xpathLocator){
        WebElement element = driver.findElement(By.xpath(xpathLocator));
        if(element.getAttribute("aria-checked").equals("false")){
            element.click();
        }
    }


    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public void sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
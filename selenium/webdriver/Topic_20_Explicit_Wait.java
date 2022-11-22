package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_20_Explicit_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
       // driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Visible() {

        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait = new WebDriverWait(driver,3);

        driver.findElement(By.cssSelector("div>button")).click();

        //wait cho icon xuat hien
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div>h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_02_InVisible() {

        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait = new WebDriverWait(driver,3);

        driver.findElement(By.cssSelector("div>button")).click();

        //wait cho icon biet mat
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div>loading")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div>h4")).getText(),"Hello World!");
    }

    @Test

    public void TC_06_Ajax_Waiting(){

        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        explicitWait = new WebDriverWait(driver,15);

        // chờ cho date picker duoc hien thị trong 15s
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));

        //verify chua chon ngay nao
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"No Selected Dates to display.");

        //wait cho đến khi click được
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='15']")));

        driver.findElement(By.xpath("//a[text()='15']")).click();

        //wait cho loading icon bien mat
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

        //wait cho ngay vua chon click duoc
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.rcSelected>a")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div.RadAjaxPanel>span")).getText(),"Tuesday, November 15, 2022");

    }

    @Test
    public void TC07(){
        explicitWait = new WebDriverWait(driver,15);

        driver.get("https://gofile.io/uploadFiles");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rowUploadButton button.uploadButton")));

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys("/Users/kemuri/Desktop/1.png");

        //wait cho loading bar biet mat
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.progress-bar")));

        //wait cho message duoc hien thi

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.callout-success h5")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div.callout-success h5")).getText(),"Your files have been successfully uploaded");







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
package webdriver.webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_17_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_() {
        //parent page
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //lay ra id cua tab hien tai
        String parentWindowID = driver.getWindowHandle();
        System.out.println(parentWindowID);

        //click vao google link de ra tab moi
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        //lay het id cua tab ra
        Set<String> allID = driver.getWindowHandles();

        //dung for loop kiem tra
        // neu khac voi parent window id thi click
        for (String id : allID){
            if(!id.equals(parentWindowID)){
                driver.switchTo().window(id);
                sleepInSecond(2);
            }
        }

        driver.findElement(By.cssSelector("input[name='q']")).sendKeys("cypress");
        sleepInSecond(5);

        //swich nguoc ve
        String otherID = driver.getWindowHandle();
        switchToWindowFrom(otherID);

    }

    @Test
    public void TC_02(){

        //parent page
        driver.get("https://automationfc.github.io/basic-form/index.html");


        //click vao google link de ra tab moi
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        switchToWindowByTitle("Google");
        driver.findElement(By.cssSelector("input[name='q']")).sendKeys("cypress");
        sleepInSecond(5);

    }

    @Test
    public void TC_03(){
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/ul//a[text()='Add to Compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/ul//a[text()='Add to Compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");


        driver.findElement(By.xpath("//span[text()='Compare']")).click();
        sleepInSecond(3);
        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Compare Products']")).getText(),"COMPARE PRODUCTS");

        driver.findElement(By.xpath("//span[text()='Close Window']")).click();

        switchToWindowByTitle("Mobile");
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();


        //switch to Alert(khi alert xuat hien)
        Alert alert = driver.switchTo().alert();

        //accept 1 alert
        alert.accept();

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).getText(),"The comparison list was cleared.");











    }




    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    //dung switch cho 2 ID
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

    //dung switch cho 2 title tab
    public void switchToWindowByTitle(String expectTitle){

        Set<String> allWindow = driver.getWindowHandles();

        for (String id: allWindow){
            //switch qua tung ID truoc
            driver.switchTo().window(id);
            //lay ra title cua page nay
            String actualPage = driver.getTitle();

            if(actualPage.equals(expectTitle)){
                sleepInSecond(3);
                break;
            }

        }

    }




    public void sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
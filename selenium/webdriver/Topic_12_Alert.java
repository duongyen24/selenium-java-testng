package webdriver.webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_12_Alert {
    WebDriver driver;
    Alert alert;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        sleepInSecond(3);

        //switch to Alert(khi alert xuat hien)
        alert = driver.switchTo().alert();

        //accept 1 alert
       alert.accept();

       //verify alert title trước khi accept alert
        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        //cancel alert
      //  alert.dismiss();

        // nhap text
       // alert.sendKeys("");

        //get alert title
       // alert.getText();

        //verify accept alert
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked an alert successfully");

    }

    @Test
    public void TC_02_Confirm_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        sleepInSecond(3);

        alert= driver.switchTo().alert();
        //verify title
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");

        //cancel alert
        alert.dismiss();

        //verify text
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked: Cancel");

    }

    @Test
    public void TC_03_Prompt_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSecond(3);

        alert = driver.switchTo().alert();

        //verify title
        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        //send text to alert
        alert.sendKeys("duongyen");
        alert.accept();

        //verify text
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: duongyen");
    }

    @Test
    public void TC_04_Authentication_Alert(){
        //pass hẳn user/password vao url trước khi open nó ra
        // http://username:password@url

        driver.get("http://the-internet.herokuapp.com/");

        String basicAuthen = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        //send user/pw vao url
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        sleepInSecond(5);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must have the proper credentials"));
    }

    @Test
    public void TC_04_Authentication_Alert_1(){
        //pass hẳn user/password vao url trước khi open nó ra
        // http://username:password@url

        driver.get("http://the-internet.herokuapp.com/");

        String basicAuthen = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        //send user/pw vao url
        driver.get(getAuthenUrl(basicAuthen,"admin", "admin"));
        sleepInSecond(5);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must have the proper credentials"));

    }





    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String getAuthenUrl(String basicAuth, String userName, String password){
        String[] authenArray = basicAuth.split("//");
        basicAuth = authenArray[0] + "//" + userName + ":" + password + "@" + authenArray[1];
        return basicAuth;
    }

    public void sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
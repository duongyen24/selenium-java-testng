package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_14_Fixed_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01() {

        driver.get("https://ngoaingu24h.vn/");
        By loginPopup = By.xpath("(//div[@id='modal-login-v1'])[1]");

        //check popup chua hien thi
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

        //click vao login button
        driver.findElement(By.cssSelector("button.login_")).click();
        sleepInSecond(3);

        //popup login hiển thị
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        //nhap thong tin k hop le
        driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//input[@name='account-input']")).sendKeys("auto");

        driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//input[@name='password-input']")).sendKeys("auto");

        driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//button[text()='Đăng nhập']")).click();
        sleepInSecond(3);

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Tài khoản không tồn tại!')]")).getText(),"Tài khoản không tồn tại!");




    }

    @Test
    public void TC_03(){
        driver.get("https://tiki.vn/");

        //check pop chua xuat  hien trong DOM( nho dung findElement so nhieu
        Assert.assertEquals(driver.findElements(By.cssSelector("div[role='dialog']")).size(),0);

        driver.findElement(By.xpath("//span[text()='Đăng Nhập / Đăng Ký']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div[role='dialog']")).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(),"Email không được để trống");

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(),"Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("img.close-img")).click();

    }

    @Test
    public void TC_04(){
        driver.get("https://www.facebook.com/");

        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSecond(3);

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());

        driver.findElement(By.cssSelector("img._8idr")).click();
        sleepInSecond(3);

        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(),0);
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
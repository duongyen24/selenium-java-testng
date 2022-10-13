package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_WebBrowser_WebElement_Command {
    WebDriver driver; // tuong tac voi trinh duyet
    WebElement element; // tuong tac voi web HTML element

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_WebBrowser_Command() {
        driver.quit();
        WebElement driver1 = driver.findElement(By.cssSelector(""));
        driver.findElements(By.cssSelector(""));
        driver.getPageSource();
        driver.manage().getCookies(); // trong bai framework
        driver.manage().window().maximize(); // mo trinh duyet max screen cho k bi fail
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

        driver.switchTo().alert();
        driver.switchTo().frame(0);

        driver.getTitle();





    }

    @Test
    public void TC2_WebElement_Command(){
        // tuong tac thong qua HTML Element
        // 2 cach:

        // khai bao bien dung lai nhieu lan
        WebElement email = driver.findElement(By.id("email"));
        email.clear();
        email.sendKeys("a@gmail.com");

        //dung truc tiep
        driver.findElement(By.id("email")).clear();

        element.isDisplayed();

        //tra ve gia tri nam trong attribute
        element.getAttribute("placeholder");

        //tim data nam trong attribute 'value'
        driver.findElement(By.id("firstName")).getAttribute("value");

        //tra ve thuoc tinh css cua element nay
        //font / size/ color
        element.getCssValue("font-size");

        element.getCssValue("background-color");

        //test GUI: point/rectangle/size(visualize testing)
        element.getLocation();
        element.getRect();

        //chup man hinh attach vao HTML report
        element.getScreenshotAs(OutputType.FILE);

        //tra ve gia tri dung or sai cua 1 element co the thao tac duoc hay k
        //co bi disabled k
        element.isEnabled();
        //Enabled: true
        //Disabled: false

        //tra ve gia tri dung or sai cua 1 element da duoc chon roi hay chua
        //checkbox / radio
        element.isSelected();
        //chon roi: true
        //chua chon: false

        //dropdown co thu vien Select xu ly

        //chi work voi form(register/login/search..)
        //submit = enter vao 1 field nao do trong form
        element.submit();




        




    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
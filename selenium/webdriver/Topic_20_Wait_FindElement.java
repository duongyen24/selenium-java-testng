package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class Topic_20_Wait_FindElement {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver,10);
    }

    @Test
    public void TC_01_FindElement() {
        //tìm thấy duy nhất 1 node
        // tìm thấy và thao tác trực tiếp lên node đó
        //khi đã tìm thấy thì k cần phải chờ hết 15s
        driver.findElement(By.cssSelector("input#email"));

        //tìm thấy nhiều hơn 1 node
        // nó sẽ thao tác lên node đầu tiên và k quan tâm node còn lại
        //trong TH bắt locator sai thì nó tìm sai
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("yen123");


        //không tìm thấy node nào
        //có cơ chế 0.5s tìm 1 lần
        //nếu trong 15s tìm thấy element thì đánh pass
        //k tìm thấy element thi dánh fail -> throw error: nosuchelement

    }

    @Test
    public void TC_02_FindElements(){

        //tìm thây duy nhất 1 node
        //tìm rồi lưu nó vào list
        //vì nó tìm thấy nên k can chờ hếttimeout
        List<WebElement> elements = driver.findElements(By.cssSelector(""));


        //tim thấy nhiều hơn 1 node
        // lưu vào list element tương ứng
        elements = driver.findElements(By.cssSelector("input"));
        System.out.println(elements.size());

        // k tìm thấy node nào
        //có cơ chế 0.5s tìm 1 lan
        //tìm thấy thì thôi
        //k thấy thì k đánh fail + vẫn chạy step tiep theo
        //tra ve list rỗng empty = 0





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
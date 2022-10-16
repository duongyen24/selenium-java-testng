package webdriver.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_Custom_Dropdown {
    WebDriver driver; //khai bao
    Select select; // khai bao
    WebDriverWait explicitWait; //khai boa
    JavascriptExecutor jsExcute;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver(); // khoi tao
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //khoi tao wait
        explicitWait = new WebDriverWait(driver,30);

        //interface thì xai cast k xài new
        jsExcute = (JavascriptExecutor) driver;
       // driver.manage().window().setSize(new Dimension(1366,768));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_06_Jquery01() {

        //1.click vao 1 phan tu nao do cho no xo ra
       // driver.findElement(By.cssSelector("span#number-button")).click();

        //2. chờ cho tất cả item trong dropdwon được load ra xong
        // note: k dùng sleep cứng
        //phải có 1 hàm wait nào đó linh động:
        // nếu chưa tìm thấy value thì sẽ tìm lại trong time đã set
        //nếu tìm thấy rồi thì k phải chờ hết time nữa
        // bắt được 1 locator để đại điện cho tất cả item (lay 1 thằng bất kì trong list)
     //   explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));

        //3.1 nếu item cần chọn đang hiển thị
        //3.2 nếu item cần chọn k hiển thị thì cần scoll-down
        //4. kiễm tra text của item- nếu đúng cái mình cần thì click vào
        //viết code duyệt qua từng item và kiểm tra theo điều kiện
        //duyet qua từng item- lấy ra text và kiểm tra nó = với text mình mong muốn thì click vào

        //luu tru tất cả item vào biến rồi mới duyệ qua được
     //   List<WebElement> items = driver.findElements(By.cssSelector("ul#number-menu div"));

        //goi hàm ra xài trong cùng 1 class

        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        //Select a number
        selectItemCustomDropdown("span#number-button","ul#number-menu div", "7");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"7");

        //Select a number
        selectItemCustomDropdown("span#speed-button","ul#speed-menu div", "Slow");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Slow");


        //Select a file
        selectItemCustomDropdown("span#files-button","ul#files-menu div", "Some unknown file");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(),"Some unknown file");

        //Select a title
        selectItemCustomDropdown("span#salutation-button","ul#salutation-menu div", "Mr.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(),"Mr.");


    }

    @Test
    public void TC_06_Jquery_02(){
        driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
        scrollDown("div.carousel-item");
        sleepInSecond(3);

        selectItemCustomDropdown("button#selectize-input","button#selectize-input+div>a","CITY L");
        sleepInSecond(3);

        scrollDown("div.container");
        sleepInSecond(3);

        //handle default dropdown
        new Select(driver.findElement(By.cssSelector("select#province"))).selectByVisibleText("Hà Nội");
        sleepInSecond(3);

        new Select(driver.findElement(By.cssSelector("select#registration_fee"))).selectByVisibleText("Khu vực I");
    }

    @Test
    public void TC_reactJS(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemCustomDropdown("div.dropdown","div.menu span.text","Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Matt");


    }

    @Test
    public void TC_vueJS(){
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemCustomDropdown("div.btn-group","ul.dropdown-menu a","First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");

    }

    @Test
    public void TC_react_editable(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        editItemCustomDropdown("input.search","div.menu span.text","Albania");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Albania");

    }


   @AfterClass
    public void afterClass() {
  //      driver.quit();
    }

    public void scrollDown(String ccssLocator){
        jsExcute.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(ccssLocator)));

    }

    // k dung cho 1 dropdown nao cu the
    // dung cho cac dropdown chung chung
    public void selectItemCustomDropdown(String parentLocator, String childLocator, String textExpectedItem){
        driver.findElement(By.cssSelector(parentLocator)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator )));
        List<WebElement> items = driver.findElements(By.cssSelector(childLocator));
        for (WebElement item : items ){
            String textItems = item.getText();
            if(textItems.equals(textExpectedItem))
                item.click();
                //break; //trong react-angular thì sẽ bị mất element đi khi đã select nên phải break để k bị lỗi
        }

    }

    public void editItemCustomDropdown(String parentLocator, String childLocator, String textExpectedItem){
        driver.findElement(By.cssSelector(parentLocator)).sendKeys(textExpectedItem);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator )));
        List<WebElement> items = driver.findElements(By.cssSelector(childLocator));
        for (WebElement item : items ){
            String textItems = item.getText();
            if(textItems.equals(textExpectedItem))
                item.click();
            //break; //trong react-angular thì sẽ bị mất element đi khi đã select nên phải break để k bị lỗi
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






















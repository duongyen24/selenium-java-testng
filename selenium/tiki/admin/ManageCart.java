package webdriver.tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ManageCart {
    WebDriver driver;

    @BeforeTest (alwaysRun = true)
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
    }

    @Test (groups = "admin",description = "jira 123", enabled = false)
    void Create_Cart(){
        System.out.println("hi");

    }

    @Test(groups = "admin")
    void View_Cart(){

    }
    @Test(groups = "admin")
    void Update_Cart(){

    }
    @Test(groups = "admin")
    void Delete_Cart(){

    }
}

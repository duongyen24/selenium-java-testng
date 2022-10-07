package webdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_WebDriver_WebElement_Excersise_2 {
    WebDriver driver; // tuong tac voi trinh duyet
    WebElement element; // tuong tac voi web HTML element

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_isDisplayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //email
        WebElement eMailTextbox = driver.findElement(By.cssSelector("input#mail"));

        if(eMailTextbox.isDisplayed()){
            eMailTextbox.sendKeys("Automation Teting");
            System.out.println("email is displayed");
        } else {
            System.out.println("email is not displayed");

        }

        //age
        WebElement ageUnder18 = driver.findElement(By.cssSelector("input#under_18"));
        if(ageUnder18.isDisplayed()){
            ageUnder18.click();
            System.out.println("Age under 18 is displayed");

        }else {
            System.out.println("age under 18 is not displayed");
        }

        //edu
        WebElement edu = driver.findElement(By.cssSelector("textarea#edu"));
        if (edu.isDisplayed()){
            edu.sendKeys("automation fc");
            System.out.println("education is displayed");
        }else{
            System.out.println("education is not displayed");
        }

        //Image 5

        WebElement image5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if (image5.isDisplayed()) {
            System.out.println("image 5 is displayed");
        } else {
            System.out.println("image 5 isnot displayed");
        }

        System.out.println(image5.isDisplayed()); // tra ve true or false


    }

    @Test
    public void TC2_is_Enabled(){
        //co the tuong tac duoc -> true
        // k tuong tac duoc -> false
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //email textbox
        WebElement emailEnabled = driver.findElement(By.cssSelector("input#mail"));
        if (emailEnabled.isEnabled()){
            System.out.println("Email is enabled");

        } else {
            System.out.println("email is disabled");
        }

        //password
        WebElement password = driver.findElement(By.cssSelector("input#disable_password"));
        if (password.isEnabled()){
            System.out.println("password is enabled");

        }else {
            System.out.println("password is disabled");
        }

        //age under 18
        WebElement age18 = driver.findElement((By.cssSelector("input#under_18")));
        if( age18.isEnabled()){
            System.out.println("age under 18 is enabled");
        } else{
            System.out.println("age under 18 is disabled");
        }

        //education
        WebElement eduEnabled = driver.findElement((By.cssSelector("textarea#edu")));
        if( eduEnabled.isEnabled()){
            System.out.println("education is enabled");
        } else{
            System.out.println("education is disabled");
        }

        //job1

        WebElement job1 = driver.findElement((By.cssSelector("select#job1")));
        if( job1.isEnabled()){
            System.out.println("job1 is enabled");
        } else{
            System.out.println("job1 is disabled");
        }

        //interest

        WebElement interest = driver.findElement((By.cssSelector("input#development")));
        if( interest.isEnabled()){
            System.out.println("interest is enabled");
        } else{
            System.out.println("interest is disabled");
        }

        //slide1

        WebElement slider1 = driver.findElement((By.cssSelector("input#slider-1")));
        if( slider1.isEnabled()){
            System.out.println("slider1 is enabled");
        } else{
            System.out.println("slider1 is disabled");
        }


        //age-disabled

        WebElement ageDisabled = driver.findElement((By.cssSelector("input#over_18")));
        if( ageDisabled.isEnabled()){
            System.out.println("age is enabled");
        } else{
            System.out.println("age is disabled");
        }

        //biography

        WebElement biography = driver.findElement((By.cssSelector("textarea#bio")));
        if( biography.isEnabled()){
            System.out.println("biography is enabled");
        } else{
            System.out.println("biography is disabled");
        }

        //job3

        WebElement job3 = driver.findElement((By.cssSelector("select#job3")));
        if( job3.isEnabled()){
            System.out.println("job3 is enabled");
        } else{
            System.out.println("job3 is disabled");
        }

        //interest

        WebElement interest1 = driver.findElement((By.cssSelector("input#check-disbaled")));
        if( interest1.isEnabled()){
            System.out.println("interest is enabled");
        } else{
            System.out.println("interest is disabled");
        }

        //slider2

        WebElement slider2 = driver.findElement((By.cssSelector("input#slider-2")));
        if( slider2.isEnabled()){
            System.out.println("slider2 is enabled");
        } else{
            System.out.println("slider2 is disabled");
        }

    }

    @Test
    public void TC3_isSelected(){

        driver.get("https://automationfc.github.io/basic-form/index.html");
        //ageSelected
        WebElement ageSelected = driver.findElement((By.cssSelector("input#under_18")));
        ageSelected.click();

        if( ageSelected.isEnabled()){
            System.out.println("ageSelected is enabled");
        } else{
            System.out.println("ageSelected is disabled");
        }

        //java checkbox
        WebElement javaCheckbox = driver.findElement((By.cssSelector("input#java")));
        javaCheckbox.click();

        if( javaCheckbox.isEnabled()){
            System.out.println("javaCheckbox is enabled");
        } else{
            System.out.println("javaCheckbox is disabled");
        }


    }

    @Test
    public void TC4_Mail_Chimp(){
        driver.get("https://login.mailchimp.com/signup/");

        //email
        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        //sleep and wait it input in username field
        sleepInSecond(3);
        // password

        WebElement password = driver.findElement(By.cssSelector("input#new_password"));
        //check lowercase
        password.sendKeys("aa");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //check uppercase
        password.clear();
        password.sendKeys("AA");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //check special character
        password.clear();
        password.sendKeys("@@");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //check number
        password.clear();
        password.sendKeys("12345");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());


        //check 8char
        password.clear();
        password.sendKeys("aaAA1#1");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());


        /*check full
        password.clear();
        password.sendKeys("aaAA1#11");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        */

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
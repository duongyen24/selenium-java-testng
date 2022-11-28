package webdriver.testng;

import org.testng.annotations.*;

public class TC01_Annotation {
    @Test()
    void TC_01(){
        System.out.println("TC 01");
    }

    @Test()
    void TC_02(){
        System.out.println("TC 02");
    }

    @BeforeMethod
    void beforeMethod(){
        System.out.println("before method");
    }

    @AfterMethod
    void afterMethod(){
        System.out.println("after method");
    }

    @BeforeClass
    void beforeClass(){
        System.out.println("before class");
    }
    @AfterClass
    void afterClass(){
        System.out.println("after class");
    }

    @BeforeTest
    void beforeTest(){
        System.out.println("before test");
    }

    @AfterTest
    void afterTest(){
        System.out.println("after test");
    }

    @BeforeSuite
    void beforeSuite(){
        System.out.println("before suite");
    }

    @AfterSuite
    void afterSuite(){
        System.out.println("after suite");
    }


}



















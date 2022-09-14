package webdriver.javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Topic_02_DataType {
    // data type nguyen thuy (k co thap phan)
    // kich thuoc/do rong luu tru tu nho den lon
    // so nguyen: byte short int long
    // k tham chieu du lieu qua thang khac - k thay doi gia tri
    public static void main(String[] args) {
        byte bNumber = 127;
        short sNumber = 32000;
        int iNumber = 12334556;
        long lNumber = 31234;

        //so thuc: flaot double (co thap phan)
        // ve luu tru: cho du lieu lon, chinh xac: dung double
        float studenPoint = 9.5f;
        double employeeSalary = 35.5d;

        //logic: boolean

        boolean status = true;
        status = false;


        //ky tu: char
        char a = 'A';

        //II - data type tham chieu (Reference)
        //class
        // duoc tham chieu du lieu tu thang khac - thay doi gia tri
        ChromeDriver driver = new ChromeDriver();

        //interface
        WebElement firstNameTextbox;

        //string
        String firstName = "automate";

        //object
        Object people;

        //array
        String[] studentName = {"A", "B", "C"};

        //collection: List/ Set/ Queue
        List<WebElement> checkbox = driver.findElements(By.cssSelector(""));

        //Map
        Map<String, Integer> student = new HashMap<String, Integer>();

    }
}


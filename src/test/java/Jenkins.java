import java.util.concurrent.TimeUnit;



    import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Jenkins {

        WebDriver driver;
        String loginUrl = "https://auto.ria.com/uk/";


        @BeforeSuite
        public void driverSetUp() {

            WebDriverManager.chromedriver().setup(); // Вместо setProperty
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            System.out.println("Our test setup is completed");
        }

        @BeforeTest
        public void profileSetup() {
            driver.manage().window().maximize();
            System.out.println("Our profile setup");
        }


        @Test
        public void autoRio() {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get(loginUrl);
            driver.findElement(By.className("ext-end")).click();
            driver.findElement(By.xpath("//label[contains(text(),'  Приймаю всі  ')]")).click();
            driver.findElement(By.xpath("//label[contains(text(),' Хетчбек')]")).click();
            driver.findElement(By.xpath("//label[@data-text='Оберіть марку']")).click();
            driver.findElement(By.xpath("//ul[@class='unstyle scrollbar autocomplete-select']//a[contains(text(),'Volkswagen')]")).click();
            driver.findElement(By.xpath("//*[@id='at_year-from']")).click();
            driver.findElement(By.xpath("//select[@id='at_year-from']//option[@value='2010']")).click();
            driver.findElement(By.xpath("//*[@id='at_year-to']")).click();
            driver.findElement(By.xpath("//select[@id='at_year-to']//option[@value='2019']")).click();

            js.executeScript("window.scrollBy(0,800)");

            driver.findElement(By.xpath("//*[@data-text='Оберіть модель']")).click();
            driver.findElement(By.xpath("//ul[@class='unstyle scrollbar autocomplete-select check_list']//a[contains(text(),'Golf')]")).click();
            driver.findElement(By.id("at_price-from")).sendKeys("10000");
            driver.findElement(By.id("at_price-to")).sendKeys("12000");

            js.executeScript("window.scrollBy(0,2000)");

            driver.findElement(By.xpath("//*[@class='el-selected']")).click();
            driver.findElement(By.xpath("//*[@class='radio-btn car-color-15']")).click();
            driver.findElement(By.xpath("//*[@class='button small']")).click();

            js.executeScript("window.scrollBy(0,1000)");

        }

        @AfterClass
        public void closeTest() {
            driver.close();
            System.out.println("Goodbye");
        }



}

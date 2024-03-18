package demo;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.google.com");

        Actions actions = new Actions(driver);

        try {
            WebElement Searchbox = driver.findElement(By.id("APjFqb"));
            Searchbox.sendKeys("calculator");
            actions.sendKeys(Keys.RETURN).perform();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("center_col")));
            if(driver.getCurrentUrl().contains("google")){
                System.out.println("The url contains google : Calculator");
            }
            System.out.println("The Google Calculator is loaded");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Failed to type calculator");
        }

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            String disp = driver.findElement(By.xpath("//span[@id='cwos']")).getText();
            if(disp.equals("0")){
                System.out.println("Initial value zero is displayed : "+ disp);
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Failed to display initial value zero");
        }

        System.out.println("end Test case: testCase01");

    }
    public void testCase02() throws InterruptedException{

        System.out.println("Start Test case: testCase02");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        try {

            WebElement fn = driver.findElement(By.xpath("//div[text()='5']"));
            fn.click();

            WebElement cp = driver.findElement(By.xpath("//div[text()='+']"));
            cp.click();

            WebElement sn = driver.findElement(By.xpath("//div[text()='7']"));
            sn.click();

            WebElement equals = driver.findElement(By.xpath("//div[text()='=']"));
            equals.click();

            WebElement DisplayResult = driver.findElement(By.xpath("//*[@id='cwos']"));
            DisplayResult.isDisplayed();
            if(DisplayResult.getText().equals("12")){
                System.out.println("TC 02 : Successfully performed addition Result : "+ DisplayResult.getText());
            }else{
                System.out.println("Invalid additional result");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Failed to perform addition");
        }

        Thread.sleep(2000);

        try {
            WebElement fne = driver.findElement(By.xpath("//div[text()='1']"));
            fne.click();

            WebElement fne_sub = driver.findElement(By.xpath("//div[text()='5']"));
            fne_sub.click();

            WebElement cm = driver.findElement(By.xpath("//div[@jsname='pPHzQc']"));
            cm.click();

            WebElement sne = driver.findElement(By.xpath("//div[text()='8']"));
            sne.click();

            WebElement equals_m = driver.findElement(By.xpath("//div[text()='=']"));
            equals_m.click();

            WebElement DisplayResult = driver.findElement(By.xpath("//*[@id='cwos']"));
            DisplayResult.isDisplayed();
            if(DisplayResult.getText().contains("7")){
                System.out.println("TC 02 : Successfully performed Substraction Result:" + " " + DisplayResult.getText());
            }else{
                System.out.println("Invalid Substraction result");
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Failed to perform Substraction");
        }

        System.out.println("end Test case: testCase02");
    }
    public void testCase03() throws InterruptedException {

        System.out.println("Start Test case: testCase03");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        try {
            WebElement AC = driver.findElement(By.xpath("//div[@aria-label='all clear']"));

            if (AC.isEnabled()){
                System.out.println("TC 03 : Successfully clicked all clear button");
                AC.click();
            }else{
                System.out.println("TC 03 : Failed to click all clear button");
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("TC 03 : Failed to click All Clear button");
        }

        try {
            WebElement DisplayZero = driver.findElement(By.xpath("//span[text()='0']"));
            DisplayZero.isDisplayed();
            System.out.println("TC 03 : Initial value zero is displayed after clicking all clear button : " + DisplayZero.getText());
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("TC 03 : Failed to display initial value zero");
        }
        Thread.sleep(2000);
        try {
            WebElement ten_fl = driver.findElement(By.xpath("//div[text()='1']"));
            ten_fl.click();
            WebElement ten_sl = driver.findElement(By.xpath("//div[text()='0']"));
            ten_sl.click();

            WebElement xBtn = driver.findElement(By.xpath("//div[@aria-label='multiply']"));
            xBtn.click();

            WebElement ThreeBtn = driver.findElement(By.xpath("//div[text()='3']"));
            ThreeBtn.click();

            WebElement clickEqualSign = driver.findElement(By.xpath("//div[text()='=']"));
            clickEqualSign.click();

            WebElement DisplayResult = driver.findElement(By.xpath("//*[@id='cwos']"));
            DisplayResult.isDisplayed();
            if(DisplayResult.getText().equals("30")){
                System.out.println("TC 03 : Successfully performed Multiplication Result: " + DisplayResult.getText());
            }else{
                System.out.println("TC 03 : Invalid Multiplication result");
            }
            Thread.sleep(2000);
            try {
                WebElement AC = driver.findElement(By.xpath("//div[@aria-label='all clear']"));

                if (AC.isEnabled()){
                    System.out.println("TC 03 : Successfully clicked all clear button");
                    AC.click();
                }else{
                    System.out.println("TC 03 : Failed to click all clear button");
                }

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("TC 03 : Failed to click All Clear button");
            }

            try {
                WebElement DisplayZero = driver.findElement(By.xpath("//span[text()='0']"));
                DisplayZero.isDisplayed();
                System.out.println("TC 03 : Initial value zero is displayed after clicking all clear button : " + DisplayZero.getText());
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("TC 03 : Failed to display initial value zero");
            }
            Thread.sleep(2000);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("TC 03 : Failed to perform multiplication");
        }
        System.out.println("End Test case: testCase03");

    }
    public void testCase04() throws InterruptedException{

        System.out.println("Start Test case: testCase04");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        try {
            WebElement tewnty_fl = driver.findElement(By.xpath("//div[text()='2']"));
            tewnty_fl.click();

            WebElement twenty_sl = driver.findElement(By.xpath("//div[text()='0']"));
            twenty_sl.click();

            WebElement divisonBtn = driver.findElement(By.xpath("//div[@jsname='WxTTNd']"));

            if(divisonBtn.isEnabled()){
                divisonBtn.click();
                System.out.println("TC 04 : Divison button successfully clicked");
            }

            WebElement FourBtn = driver.findElement(By.xpath("//div[text()='4']"));

            FourBtn.isDisplayed();
            if(FourBtn.getText().equals("4")){
                FourBtn.click();
                System.out.println("TC 04 : Successfully clicked : " + FourBtn.getText());
            }
        } catch (Exception e) {
            System.out.println("TC 04 : Failed to click perform division operation");
        }


        try {
            WebElement clickEqualSign = driver.findElement(By.xpath("//div[text()='=']"));
            clickEqualSign.click();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("TC 04 : Failed to click equals to sign");
        }

        try {

            WebElement DisplayResult = driver.findElement(By.xpath("//*[@id='cwos']"));
            DisplayResult.isDisplayed();
            if(DisplayResult.getText().equals("5")){
                System.out.println("TC 04 : Successfully performed division Result: " + DisplayResult.getText());
            }else{
                System.out.println("TC 04 : Invalid division result");
            }

        } catch (Exception e) {
            System.out.println("TC 04 : Failed to display the result");
        }
        Thread.sleep(2000);
        System.out.println("End of testcase 04");

    }



}

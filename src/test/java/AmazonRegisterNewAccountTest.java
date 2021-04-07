import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.jboss.aerogear.security.otp.Totp;

import java.util.*;
import java.util.concurrent.*;

public class AmazonRegisterNewAccountTest {

    public static final String CHROME_DRIVER_PATH = "D:\\chromedriver.exe";
    public static final String AMAZON_HOMEPAGE = "https:\\amazon.com";
    WebDriver driver;
    Random randomGenerator = new Random();
    public int randomInt = randomGenerator.nextInt(10000);
    public final String PASS = "Pass1234567890";

    String otpKeyStr = "6jm7n6xwitpjooh7ihewyyzeux7aqmw2"; // <- this 2FA secret key.

    Totp totp = new Totp(otpKeyStr);
    String twoFactorCode = totp.now(); // <- got 2FA coed at this time!


    @BeforeEach
    public void navigateToHomepage() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver" , CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(AMAZON_HOMEPAGE);
    }

    @Test
    public void registerNewAccount() {

        WebElement signInBtn = driver.findElement(By.xpath("//span[@class=\"nav-line-2 nav-long-width\"]"));
        signInBtn.click();

        driver.manage().timeouts().implicitlyWait(5 , TimeUnit.SECONDS);
        WebElement newAccount = driver.findElement(By.xpath("//a[@class=\"a-button-text\"]"));

        newAccount.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("ap_customer_name")).sendKeys("Mateusz");
        driver.findElement(By.id("ap_email")).sendKeys("usernamex" + randomInt + "@gmail.com");
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys(PASS);
        driver.findElement(By.xpath("//input[@id=\"ap_password_check\"]")).sendKeys(PASS);
        driver.findElement(By.className("a-button-input")).click();
        driver.findElement(By.xpath("//input[@name=\"code\"]")).sendKeys(twoFactorCode);
        driver.findElement(By.className("a-button-input")).click();























//        WebElement register = driver.findElement(By.xpath("//a[@href=\"https://www.sklepopon.com/rejestracja.html\"]"));
//        register.click();
//        driver.findElement(By.id("imie")).sendKeys("Panna");
//        driver.findElement(By.id("nazwisko")).sendKeys("Konstantynopolitańczykowianeczka");
//        driver.findElement(By.id("email2")).sendKeys("usernamex" + randomInt + "@gmail.com");
//        driver.manage().timeouts().implicitlyWait(4 , TimeUnit.SECONDS);
//        driver.findElement(By.id("email_check")).sendKeys("usernamex" + this.randomInt + "@gmail.com");
//        driver.findElement(By.xpath("//input[@name=\"formData[haslo]\"]")).sendKeys("Haslo123.");
//        driver.findElement(By.xpath("//input[@name=\"formData[haslo2]\"]")).sendKeys("Haslo123.");
//
//        if (!driver.findElement(By.xpath("//input[@name=\"formData[regulamin]\" and @id=\"accept-rules\" and @value=\"0\" and @type=\"checkbox\" and @class=\"floatLeft\"]")).isSelected()) {
//
//            driver.findElement(By.xpath("//input[@name=\"formData[regulamin]\" and @id=\"accept-rules\" and @value=\"0\" and @type=\"checkbox\" and @class=\"floatLeft\"]")).sendKeys(Keys.SPACE);
//        }
//        driver.findElement(By.xpath("//a[@href=\"#\" and @onclick=\"wyslijRej();return false;\"]")).click();
//        driver.manage().timeouts().implicitlyWait(4 , TimeUnit.SECONDS);
//

    }

//    @AfterEach
//    public void zamknijBrowser() {
//
//        driver.quit();
//    }
}


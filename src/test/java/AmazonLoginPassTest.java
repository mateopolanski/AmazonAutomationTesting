import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.*;


public class AmazonLoginPassTest {


    public static final String CHROME_DRIVER_PATH = "D:\\chromedriver.exe";
    public static final String AMAZON_HOMEPAGE = "https:\\amazon.com";
    WebDriver driver;
    public final String ACCOUNT_EMAIL = "dxc03609@cuoly.com";
    public final String AUTH_PASS = "Password12345";


    @BeforeEach
    public void navigateToHomepage() {

        System.setProperty("webdriver.chrome.driver" , CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(AMAZON_HOMEPAGE);

    }

    @Test
    public void LoginToExistingAccount() {

        WebElement signInBtn = driver.findElement(By.xpath("//span[@class=\"nav-line-2 nav-long-width\"]"));
        signInBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_email"))).sendKeys(ACCOUNT_EMAIL);
        driver.findElement(By.className("a-button-input")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_password")));

        driver.findElement(By.id("ap_password")).sendKeys(AUTH_PASS);
        driver.findElement(By.id("signInSubmit")).click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement signName = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
        String match = signName.getText();
        Assertions.assertEquals("Hello, AmazonTester", match);
    }
}


import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.util.*;
import java.util.concurrent.*;

 class AmazonLoginWrongCredentialsTest {

    private static final String CHROME_DRIVER_PATH = "D:\\chromedriver.exe";
    private static final String AMAZON_HOMEPAGE = "https:\\amazon.com";
    private WebDriver driver;
    private final String ACCOUNT_EMAIL = "dxc03609@cuoly.com";
    private Random randomGenerator = new Random();
    private int badAuthorizationPass = randomGenerator.nextInt(10000);


    @BeforeEach
     void navigateToHomepage() {

        System.setProperty("webdriver.chrome.driver" , CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(AMAZON_HOMEPAGE);

    }

    @Test
     void unsuccessfullLoginToExistingAccount() {

        WebElement signInBtn = driver.findElement(By.xpath("//span[@class=\"nav-line-2 nav-long-width\"]"));
        signInBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_email"))).sendKeys(ACCOUNT_EMAIL);
        driver.findElement(By.className("a-button-input")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_password")));

        driver.findElement(By.id("ap_password")).sendKeys("pass"+badAuthorizationPass);
        driver.findElement(By.id("signInSubmit")).click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement wrongPswd = driver.findElement(By.className("a-list-item"));
        String alert = wrongPswd.getText();
        Assertions.assertEquals("Your password is incorrect", alert);

        /*
        Program should not log into account as an password authorisation key is not correct. Valid password is given
        in AmazonLoginPassTest.class
         */
    }

    @AfterEach
     void closeDriver () {

        driver.quit();
    }
}
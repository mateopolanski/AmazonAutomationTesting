import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

public class AmazonSearchList {

    public static final String CHROME_DRIVER_PATH = "D:\\chromedriver.exe";
    public static final String AMAZON_HOMEPAGE = "https:\\amazon.com";
    WebDriver driver;
    public static final String SEARCHED_ITEM = "cheyenne";

    @BeforeEach
    public void navigateToHomepage() {

        System.setProperty("webdriver.chrome.driver" , CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(AMAZON_HOMEPAGE);

    }

    @Test
    public void searchForItem() {

        WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
        searchBar.sendKeys(SEARCHED_ITEM);
        searchBar.sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//span[text()=\"Tattoo Machine Parts\"]")).click();
        WebDriverWait waiter = new WebDriverWait(driver, 2);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=\"Cheyenne\"]"))).click();
        driver.findElement(By.xpath("//span[text()=\"Cheyenne Tattoo Machine Hawk Pen - Black\"]")).click();
        String product = driver.findElement(By.id("productTitle")).getText();
        Assertions.assertTrue(product.contains("Cheyenne Tattoo Machine Hawk Pen - Black"));
        String brand = driver.findElement(By.id("bylineInfo")).getText();
        Assertions.assertTrue(brand.equals("Brand: Cheyenne"));

//        WebDriverWait wait = new WebDriverWait(driver, 3);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_email"))).sendKeys(ACCOUNT_EMAIL);
//        driver.findElement(By.className("a-button-input")).click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_password")));
//
//        driver.findElement(By.id("ap_password")).sendKeys(AUTH_PASS);
//        driver.findElement(By.id("signInSubmit")).click();
    }
}
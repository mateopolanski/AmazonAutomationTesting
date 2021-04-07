
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.asserts.SoftAssert;


 class AmazonSearchList {

    private static final String AMAZON_PAGE_TITLE = "Amazono.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
    private static final String CHROME_DRIVER_PATH = "D:\\chromedriver.exe";
    private static final String AMAZON_HOMEPAGE = "https:\\amazon.com";
    private WebDriver driver;
    private static final String SEARCHED_ITEM = "cheyenne";

    @BeforeEach
     void navigateToHomepage() {

        System.setProperty("webdriver.chrome.driver" , CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(AMAZON_HOMEPAGE);
        String title = driver.getTitle();
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(AMAZON_PAGE_TITLE, title);


    }

    @Test
     void searchForItem() {

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

    }

    @AfterEach
     void closeDriver () {

        driver.quit();
    }
}
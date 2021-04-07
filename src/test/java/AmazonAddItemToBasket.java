import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.*;

 class AmazonAddItemToBasket {

    private static final String CHROME_DRIVER_PATH = "D:\\chromedriver.exe";
    private static final String AMAZON_HOMEPAGE = "https:\\amazon.com";
    private WebDriver driver;
    private static final String INK_ITEM = "tattoo ink";
    private static final String CARTRIDGE_ITEM = "tattoo cartridge";
    private static final String CARTRIDGE_SET = "//span[text()=\"Dragonhawk Mast Tattoo Cartridges Needles 50pcs " +
            "Round Liner Mixed 3RL 5RL 7RL 9RL 11RL Size for Machine Kit Supply (50pcs RL)\"]";
    private static final String ADD_ITEM_TO_BASKET = "add-to-cart-button";


    @BeforeEach
     void navigateToHomepage() {

        System.setProperty("webdriver.chrome.driver" , CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(AMAZON_HOMEPAGE);

    }

    @Test
     void addItems() {

        WebElement searchItem1 = driver.findElement(By.id("twotabsearchtextbox"));
        searchItem1.sendKeys(INK_ITEM);
        searchItem1.sendKeys(Keys.ENTER);

        driver.manage().timeouts().implicitlyWait(2 , TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 2);
        WebElement product1 = driver.findElement(By.xpath("//span[text()=\"Mom's Black Onyx Tattoo Ink - 1/2oz\"]"));
        product1.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(ADD_ITEM_TO_BASKET))).click();
        driver.manage().timeouts().implicitlyWait(3 , TimeUnit.SECONDS);
        WebElement searchItem2 = driver.findElement(By.id("twotabsearchtextbox"));
        searchItem2.sendKeys(INK_ITEM);
        searchItem2.sendKeys(Keys.ENTER);
        WebElement product2 = driver.findElement(By.xpath("//span[text()=\"Dynamic Black Tattoo Ink Bottle 8oz\"]"));
        product2.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(ADD_ITEM_TO_BASKET))).click();
        driver.manage().timeouts().implicitlyWait(2 , TimeUnit.SECONDS);
        WebElement searchItem3 = driver.findElement(By.id("twotabsearchtextbox"));
        searchItem3.sendKeys(CARTRIDGE_ITEM);
        searchItem3.sendKeys(Keys.ENTER);
        WebElement product3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CARTRIDGE_SET)));
        product3.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(ADD_ITEM_TO_BASKET))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hlb-view-cart-announce"))).click();
        String subtotal = driver.findElement(By.id("sc-subtotal-label-activecart")).getText();

        Assertions.assertEquals("Subtotal (3 items):", subtotal);
    }

    @AfterEach
     void closeDriver () {

        driver.quit();
    }
}

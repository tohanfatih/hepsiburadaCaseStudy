package theinternet;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.openqa.selenium.WebElement.*;

public class CustomSteps{

    private WebDriver driver;

    @After
    public void tearDown() {
        if (driver!=null) {
            driver.close();
            driver.quit();
        }
    }

    @When("Open with Chrome")
    public void setUpChrome() {
        System.setProperty("webdriver.chrome.driver",
                Paths.get("src/test/resources/environment/chromedriver.exe").toString());
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @When("Open with Firefox")
    public void setUpFirefox() {
        System.setProperty("webdriver.gecko.driver",
                Paths.get("src/test/resources/environment/geckodriver.exe").toString());
        if (driver == null) {
            driver = new FirefoxDriver();
        }
    }

    @When("Open Hepsiburada page")
    public void openPage() {
        driver.navigate().to("https://www.hepsiburada.com/");
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://www.hepsiburada.com/" );
    }

    @When("Login with test user")
    public void clickLogin(){
        driver.findElement(By.xpath("//div[@id='myAccount']")).click();
        driver.findElement(By.xpath("//a[@id='login']")).click();
        String URL = driver.getCurrentUrl();
        URL.contains("https://giris.hepsiburada.com/");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("test@gmail.com");
        driver.findElement(By.xpath("//button[@name='btnLogin']")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//div[contains(text(),'hata')]")).isDisplayed();
        driver.findElement(By.xpath("//a[@href='https://www.hepsiburada.com']")).click();
        String URLhomepage = driver.getCurrentUrl();
        Assert.assertEquals(URLhomepage, "https://www.hepsiburada.com/" );
    }

    @When("Search product")
    public void search(){
        driver.findElement(By.xpath("//input[@class='desktopOldAutosuggestTheme-input']")).sendKeys("cep telefonu");
        driver.findElement(By.xpath("//input[@class='desktopOldAutosuggestTheme-input']")).sendKeys(Keys.ENTER);
    }

    @When("Filter product")
    public void filter() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@placeholder='En az']")).sendKeys("3000");
        driver.findElement(By.xpath("(//input[contains(@placeholder,'En')])[2]")).sendKeys("5000");
        driver.findElement(By.xpath("//button[@kind='primary']")).click();
        Thread.sleep(5000);
    }

    @When("Go to bottom of the list and select random product which in the last row")
    public void scrollThenSelect(){
        String FilterCount = driver.findElement(By.xpath("//b[@class='searchResultSummaryBar-bold']")).getText();
        int i = Integer.parseInt(FilterCount);
        int randomProductId = ThreadLocalRandom.current().nextInt(i-4, i-1);
        int a;
        for (a=0;a<i;a++)
        {
            WebElement element = driver.findElement(By.xpath("//li[@id='i"+a+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }
        driver.findElement(By.xpath("//li[@id='i"+randomProductId+"']")).click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    @When("Select lowest rated seller and add product to basket then check shopping cart")
    public void showAllMerchants() throws InterruptedException {
        String productName = driver.findElement(By.xpath("//h1[@id='product-name']")).getText();
        Boolean isAnotherSellerExists = driver.findElement(By.xpath("//a[@class='optionsLength']")).isDisplayed();
        if (isAnotherSellerExists == true){
            driver.findElement(By.xpath("//a[@class='optionsLength']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//td//a[contains(text(),'Sat')]")).click();
            driver.findElement(By.xpath("//td//a[contains(text(),'Sat')]")).click();
            driver.findElement(By.xpath("(//button[@class='add-to-basket button'])[1]")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//i[@class='close']")).click();    // it closes popup
            Thread.sleep(3000);
            driver.findElement(By.xpath("//span[@id='shoppingCart']")).click(); // go to basket
        }
        else{
            driver.findElement(By.xpath("//button[@id='addToCart']")).click();
            driver.findElement(By.xpath("//span[@id='shoppingCart']")).click();
        }
        String checkBasket = driver.findElement(By.xpath("(//a[contains(@href,'magaza=')])[2]")).getText();
        Assert.assertEquals(productName,checkBasket);
    }

}
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


/**
 * Created by user on 17.01.2018.
 */
public class notWorkTest {
    WebDriver driver;
    String baseURL;

    @Before
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        baseURL = "http://www.sberbank.ru/ru/person";
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseURL);

    }
    @Test
    public void testMetod(){
        driver.findElement(By.xpath("//*/li/a[contains(@aria-label, \"Застраховать себя и имущество\"/]")).click();
        driver.findElement(By.xpath("//*[@id=\"rgs-main-menu-insurance-dropdown\"]/div[1]/div[1]/div/div[1]/div[3]/ul/li[2]/a")).click();

        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        WebElement sendBtn = driver.findElement(By.xpath("//*[@id=\"rgs-main-context-bar\"]/div/div/div/a[3]"));
        wait.until(ExpectedConditions.visibilityOf(sendBtn)).click();

        WebElement title = driver.findElement(By.xpath("//h4[@class='modal-title']"));
        wait.until(ExpectedConditions.visibilityOf(title));
        Assert.assertEquals("Заявка на добровольное медицинское страхование", title.getText());

        fillField(By.name("LastName"), "Иванов");
        fillField(By.name("FirstName"), "Иван");
        fillField(By.name("MiddleName"), "Иванович");

        new Select(driver.findElement(By.name("Region"))).selectByVisibleText("Москва");

        fillField(By.name("Comment"), "Autotest");
        fillField(By.name("Email"), "123456789");
        driver.findElement(By.xpath("//input[@class='checkbox']")).click();
        driver.findElement(By.id("button-m")).click();

        Assert.assertEquals("Введите адрес электронной почты", driver.findElement(By.xpath("//*[@id=\"applicationForm\"]/div[2]/div[6]/div/label/span")).getText());
        Assert.assertEquals("Иванов", driver.findElement(By.name("LastName")).getAttribute("value"));
        Assert.assertEquals("Иван", driver.findElement(By.name("FirstName")).getAttribute("value"));
        Assert.assertEquals("Ивановч", driver.findElement(By.name("MiddleName")).getAttribute("value"));

    }
    public void fillField(By locator, String value){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    @After
    public void afterTest(){
        driver.quit();

    }
}

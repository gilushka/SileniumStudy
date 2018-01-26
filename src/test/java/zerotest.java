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
public class zerotest {
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
        driver.findElement(By.xpath("//div[contains(@class,'header-container')]/div/div/div/div/div/ul/li/a/span/span[contains(text(),'Застраховать себя')]")).click();
        driver.findElement(By.xpath("//div[contains(@class,'header-container')]/div/div/div/div/div/ul/li/div/div/div/div/a[contains(text(),'Страхование путешественников')]")).click();

        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        WebElement title = driver.findElement(By.xpath("//div[contains(@class,'sbrf-rich-outer')]/h1"));
        wait.until(ExpectedConditions.visibilityOf(title));
        Assert.assertEquals("Страхование путешественников", title.getText());

        driver.findElement(By.xpath("//a[contains(text(),'Оформить сейчас')]")).click();

        for (String handle : driver.getWindowHandles()){ //Переключение экрана
            driver.switchTo().window(handle);
        }

        driver.findElement(By.xpath("//div[contains(text(),'35')]")).click();
        driver.findElement(By.xpath("//span[contains(@class,'b-button-block-center')]/span[contains(text(),'Оформить')]")).click();

        fillField(By.name("insured0_surname"), "Иванов");
        fillField(By.name("insured0_name"), "Иван");
        fillField(By.name("insured0_birthDate"), "19.05.1985");

        fillField(By.name("surname"), "Петров");
        fillField(By.name("name"), "Петр");
        fillField(By.name("middlename"), "Петрович");
        driver.findElement(By.name("//li[contains(@class,'alt-menu-collapser_opened')]/a/span/span[contains(text(),'Застраховать себя')]")).click();
        fillField(By.name("birthDate"), "19.05.1985");

        fillField(By.name("passport_series"), "1825");
        fillField(By.name("passport_number"), "260118");
        fillField(By.name("issueDate"), "09.06.2005");
        fillField(By.name("issuePlace"), "olala!");


/*        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        WebElement sendBtn = driver.findElement(By.xpath("//*[@id=\"rgs-main-context-bar\"]/div/div/div/a[3]"));
        wait.until(ExpectedConditions.visibilityOf(sendBtn)).click();

        WebElement title = driver.findElement(By.xpath("//h4[@class='modal-title']"));
        wait.until(ExpectedConditions.visibilityOf(title));
        Assert.assertEquals("Заявка на добровольное медицинское страхование", title.getText());*/



//        new Select(driver.findElement(By.name("Region"))).selectByVisibleText("Москва");

/*        fillField(By.name("Comment"), "Autotest");
        fillField(By.name("Email"), "123456789");
        driver.findElement(By.xpath("//input[@class='checkbox']")).click();
        driver.findElement(By.id("button-m")).click();*/

//        Assert.assertEquals("Введите адрес электронной почты", driver.findElement(By.xpath("//*[@id=\"applicationForm\"]/div[2]/div[6]/div/label/span")).getText());
//        Assert.assertEquals("Иванов", driver.findElement(By.name("LastName")).getAttribute("value"));
//        Assert.assertEquals("Иван", driver.findElement(By.name("FirstName")).getAttribute("value"));
//        Assert.assertEquals("Ивановч", driver.findElement(By.name("MiddleName")).getAttribute("value"));

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
